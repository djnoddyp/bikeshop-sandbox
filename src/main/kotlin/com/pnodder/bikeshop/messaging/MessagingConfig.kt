package com.pnodder.bikeshop.messaging

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule
import com.pnodder.bikeshop.domain.Order
import org.springframework.amqp.core.BindingBuilder
import org.springframework.amqp.core.Queue
import org.springframework.amqp.core.TopicExchange
import org.springframework.amqp.rabbit.connection.ConnectionFactory
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter
import org.springframework.amqp.support.converter.DefaultClassMapper
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter
import org.springframework.amqp.support.converter.MessageConverter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor


@Configuration
class MessagingConfig {

    companion object {
        const val QUEUE_NAME = "bikeshop-orders"
        const val TOPIC_EXCHANGE_NAME = "bikeshop-exchange"
    }

    /**
     * Rabbit MQ beans
     */
    @Bean
    fun queue() = Queue(QUEUE_NAME, false)

    @Bean
    fun exchange() = TopicExchange(TOPIC_EXCHANGE_NAME)

    @Bean
    fun binding(queue: Queue, topicExchange: TopicExchange) =
            BindingBuilder.bind(queue).to(topicExchange).with("bike.#")!!

    @Bean
    fun container(connectionFactory: ConnectionFactory, listenerAdapter: MessageListenerAdapter): SimpleMessageListenerContainer {
        val container = SimpleMessageListenerContainer()
        container.connectionFactory = connectionFactory
        container.setQueueNames(QUEUE_NAME)
        container.setMessageListener(listenerAdapter)
        return container
    }

    @Bean
    fun listenerAdapter(receiver: OrderReceiver): MessageListenerAdapter {
        val adapter = MessageListenerAdapter(receiver, "receiveOrder")
        adapter.setMessageConverter(jsonConverterWithDefaultType())
        return adapter
    }

    /**
     * Executor for handling order messages
     */
    @Bean
    fun taskExecutor(): ThreadPoolTaskExecutor {
        val taskExecutor = ThreadPoolTaskExecutor()
        taskExecutor.corePoolSize = 5
        taskExecutor.maxPoolSize = 10
        taskExecutor.setQueueCapacity(100)
        return taskExecutor
    }

    /**
     * Beans to get Jackson to handle Java 8 dates correctly
     */
    @Bean
    fun jsonConverterWithDefaultType(): MessageConverter {
        val converter = Jackson2JsonMessageConverter(objectMapper())
        converter.setClassMapper(classMapper())
        return converter
    }

    @Bean
    fun classMapper(): DefaultClassMapper {
        val classMapper = DefaultClassMapper()
        classMapper.setDefaultType(Order::class.java)
        classMapper.setTrustedPackages("com.pnodder.bikeshop.domain")
        return classMapper
    }

    @Bean
    fun objectMapper(): ObjectMapper = ObjectMapper()
            .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
            .registerModule(JavaTimeModule())
            .registerModule(Jdk8Module())
            .registerModule(ParameterNamesModule())

}


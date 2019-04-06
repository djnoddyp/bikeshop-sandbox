package com.pnodder.bikeshop.messaging

import com.pnodder.bikeshop.domain.Order
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor
import org.springframework.stereotype.Component
import java.time.LocalDateTime

@Component
class OrderReceiver(val taskExecutor: ThreadPoolTaskExecutor, private val rabbitTemplate: RabbitTemplate) {

    val log: Logger = LoggerFactory.getLogger(OrderReceiver::class.java)

    fun receiveOrder(order: Order) {
        log.info("Received order for: ${order.customer.email} at ${LocalDateTime.now()}")
    }

}

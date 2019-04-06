package com.pnodder.bikeshop.messaging

import com.pnodder.bikeshop.domain.Order
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.stereotype.Component

@Component
class OrderSender(private val rabbitTemplate: RabbitTemplate) {

    val log: Logger = LoggerFactory.getLogger(OrderSender::class.java)

    fun sendOrder(order: Order) {
        log.info("Order arrived: sending to order queue.")
        rabbitTemplate.convertAndSend(MessagingConfig.TOPIC_EXCHANGE_NAME, "bike.new.order", order)
        log.info("Order sent to order queue.")
    }

}


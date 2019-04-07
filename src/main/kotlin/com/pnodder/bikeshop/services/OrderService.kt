package com.pnodder.bikeshop.services

import com.pnodder.bikeshop.dao.OrderDao
import com.pnodder.bikeshop.domain.Order
import com.pnodder.bikeshop.messaging.OrderSender
import org.springframework.stereotype.Service

@Service
class OrderService(private val orderDao: OrderDao, private val orderSender: OrderSender) {

    fun save(order: Order) = orderDao.insert(order)

    fun notifyOrderArrival(order: Order) = orderSender.sendOrder(order)

    fun findAll(): List<Order>? = orderDao.findAll()

}
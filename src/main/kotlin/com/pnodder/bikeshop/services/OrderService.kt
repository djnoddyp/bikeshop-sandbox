//package com.pnodder.bikeshop.services
//
//import com.pnodder.bikeshop.domain.Order
//import com.pnodder.bikeshop.messaging.OrderSender
//import com.pnodder.bikeshop.repositories.OrderRepository
//import org.springframework.stereotype.Service
//
//@Service
//class OrderService(private val repository: OrderRepository, private val orderSender: OrderSender) {
//
//    fun save(order: Order) = repository.save(order)
//    fun notifyOrderArrival(order: Order) = orderSender.sendOrder(order)
//    fun findAll(): MutableIterable<Order> = repository.findAll()
//
//}
package com.pnodder.bikeshop.controllers

import com.pnodder.bikeshop.domain.Order
import com.pnodder.bikeshop.services.OrderService
import org.springframework.web.bind.annotation.*

@RestController
@CrossOrigin
class OrderController(private val orderService: OrderService) {

    @PostMapping("/orders")
    fun newOrder(@RequestBody order: Order) {
        orderService.notifyOrderArrival(order)
        orderService.save(order)
    }

    @GetMapping("/orders")
    fun orders() = orderService.findAll()

}


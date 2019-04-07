package com.pnodder.bikeshop.controllers

import com.pnodder.bikeshop.services.CustomerService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class CustomerController(private val customerService: CustomerService) {

    @GetMapping("/customers/{id}")
    fun getCustomer(@PathVariable id: Int) = customerService.findById(id)

    @GetMapping("/customers")
    fun getCustomers() = customerService.findAll()

}
//package com.pnodder.bikeshop.services
//
//import com.pnodder.bikeshop.domain.Customer
//import com.pnodder.bikeshop.repositories.CustomerRepository
//import org.springframework.stereotype.Service
//
//@Service
//class CustomerService(private val customerRepository: CustomerRepository) {
//    
//    fun findById(id: Int) = customerRepository.findById(id)
//    fun findAll(): MutableIterable<Customer> = customerRepository.findAll()
//    fun save(c: Customer) = customerRepository.save(c)
//
//}
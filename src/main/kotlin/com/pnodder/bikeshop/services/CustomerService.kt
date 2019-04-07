package com.pnodder.bikeshop.services

import com.pnodder.bikeshop.dao.CustomerDao
import com.pnodder.bikeshop.domain.Customer
import org.springframework.stereotype.Service

@Service
class CustomerService(private val customerDao: CustomerDao) {

    fun findById(id: Int) = customerDao.findById(id)

    fun findAll(): List<Customer>? = customerDao.findAll()

    fun save(c: Customer) = customerDao.insert(c)

}
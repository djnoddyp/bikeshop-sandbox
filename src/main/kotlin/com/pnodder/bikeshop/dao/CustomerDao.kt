package com.pnodder.bikeshop.dao

import com.pnodder.bikeshop.domain.Customer
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Component

@Component
class CustomerDao(private val jdbcTemplate: JdbcTemplate) : BaseDao<Customer> {

    override fun findById(id: Int): Customer? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun findAll(): List<Customer>? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun insert(obj: Customer): Int {
        return jdbcTemplate.update("INSERT INTO customer (first_name, last_name, email) VALUES (?, ?, ?)",
                obj.firstName, obj.lastName, obj.email)
    }

    override fun insertAll(objs: List<Customer>): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun insertBatch(batch: List<Customer>): Array<out IntArray> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun delete(obj: Customer): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun update(obj: Customer): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun count(): Int? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
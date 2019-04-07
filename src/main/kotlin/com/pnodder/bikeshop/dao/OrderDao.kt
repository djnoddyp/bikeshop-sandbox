package com.pnodder.bikeshop.dao

import com.pnodder.bikeshop.domain.Order
import org.slf4j.LoggerFactory
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import org.springframework.stereotype.Component
import java.sql.ResultSet

@Component
class OrderDao(private var namedParamJdbcTemplate: NamedParameterJdbcTemplate) : BaseDao<Order> {

    private val log = LoggerFactory.getLogger(OrderDao::class.java)

    val orderRowMapper = { rs: ResultSet, row: Int ->
        Order(rs.getInt("id"), rs.getInt("customer_id"), rs.getInt("bike_id"),
                rs.getDate("orderDate").toLocalDate(), rs.getDouble("totalPrice"))
    }

    override fun findById(id: Int): Order? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun findAll(): List<Order>? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun insert(obj: Order): Int {
        log.info("Inserting Order: {}", obj)
        val namedParams = mapOf("customerId" to obj.customerId, "bikeId" to obj.bikeId, "orderDate" to obj.orderDate,
                "totalPrice" to obj.totalPrice)
        val sql = "INSERT INTO customer_order (customer_id, bike_id, order_date, total_price) " +
                "VALUES (:customerId, :bikeId, :orderDate, :totalPrice)"
        return namedParamJdbcTemplate.update(sql, namedParams)
    }

    override fun insertAll(objs: List<Order>): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun insertBatch(batch: List<Order>): Array<out IntArray> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun delete(obj: Order): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun update(obj: Order): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun count(): Int? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
package com.pnodder.bikeshop.dao

import com.pnodder.bikeshop.domain.Bike
import com.pnodder.bikeshop.enums.Colour
import com.pnodder.bikeshop.enums.Style
import org.slf4j.LoggerFactory
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import org.springframework.stereotype.Component
import java.sql.PreparedStatement
import java.sql.ResultSet

@Component
class BikeDao(private val jdbcTemplate: JdbcTemplate, private val namedParamJdbcTemplate: NamedParameterJdbcTemplate) : BaseDao<Bike> {

    private val log = LoggerFactory.getLogger(BikeDao::class.java)

    val bikeStatementSetter = { ps: PreparedStatement, b: Bike ->
        ps.setString(1, b.make)
        ps.setString(2, b.model)
        ps.setInt(3, b.colour.ordinal)
        ps.setInt(4, b.style.ordinal)
        ps.setDouble(5, b.price)
        ps.setInt(6, b.stock)
    }

    val bikeRowMapper = { rs: ResultSet, row: Int ->
        Bike(rs.getInt("id"), rs.getString("make"), rs.getString("model"),
                Colour.values()[rs.getInt("colour")], Style.values()[rs.getInt("style")],
                rs.getDouble("price"),
                rs.getInt("stock"))
    }

    override fun findById(id: Int): Bike? {
        val namedParams = mapOf("id" to id)
        return namedParamJdbcTemplate.queryForObject("SELECT * FROM bike WHERE id = :id", namedParams, bikeRowMapper)
    }

    override fun findAll(): MutableList<Bike> {
        return jdbcTemplate.query("SELECT * FROM bike", bikeRowMapper)
    }

    fun findByMake(make: String): MutableList<Bike> {
        val namedParams = mapOf("make" to make)
        return namedParamJdbcTemplate.query("SELECT * FROM bike WHERE make = :make", namedParams, bikeRowMapper)
    }

    override fun delete(obj: Bike): Int {
        return jdbcTemplate.update("DELETE FROM bike WHERE id = ?", obj.id)
    }

    override fun update(obj: Bike): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun insert(obj: Bike): Int {
        log.info("Inserting bike: {}", obj)
        val sql = "INSERT INTO bike (make, model, colour, style, price, stock) VALUES (?, ?, ?, ?, ?, ?)"
        return jdbcTemplate.update(sql, obj.make, obj.model, obj.colour.ordinal, obj.style.ordinal, obj.price,
                obj.stock)
    }

    override fun insertAll(objs: List<Bike>): Int {
        var rows = 0
        for (bike in objs) {
            rows += insert(bike)
        }
        return rows
    }

    override fun insertBatch(batch: List<Bike>): Array<out IntArray> {
        val sql = "INSERT INTO bike (make, model, colour, style, price, stock) VALUES (?, ?, ?, ?, ?, ?)"
        return jdbcTemplate.batchUpdate(sql, batch, 25, bikeStatementSetter);
    }

    override fun count(): Int? {
        return jdbcTemplate.queryForObject("SELECT COUNT(*) FROM bike", Int::class.java)
    }
}

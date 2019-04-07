package com.pnodder.bikeshop

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.core.env.Environment
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import org.springframework.jdbc.datasource.DriverManagerDataSource
import javax.sql.DataSource

@SpringBootApplication
class BikeShopApplication {

    @Autowired
    lateinit var env: Environment

    @Bean
    fun dataSource(): DataSource {
        val dataSource = DriverManagerDataSource()
        dataSource.url = env.getProperty("spring.datasource.url")
        dataSource.username = env.getProperty("spring.datasource.username")
        dataSource.password = env.getProperty("spring.datasource.password")
        return dataSource
    }

    @Bean
    fun jdbcTemplate() = JdbcTemplate(dataSource())
    
    @Bean
    fun namedParamJdbcTemplate() = NamedParameterJdbcTemplate(dataSource())

}

fun main(args: Array<String>) {
    runApplication<BikeShopApplication>(*args)
}
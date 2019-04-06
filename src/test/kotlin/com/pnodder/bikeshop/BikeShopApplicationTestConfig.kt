package com.pnodder.bikeshop

import com.pnodder.bikeshop.dao.BikeDao
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType
import javax.sql.DataSource

@Configuration
@Profile("dev")
class BikeShopApplicationTestConfig {
    
    @Bean
    fun dataSource(): DataSource = EmbeddedDatabaseBuilder()
            .setType(EmbeddedDatabaseType.H2)
            .addScript("classpath:sql/test-schema.sql")
            .build()
    
    @Bean fun jdbcTemplate() = JdbcTemplate(dataSource())
    @Bean fun namedParamJdbcTemplate() = NamedParameterJdbcTemplate(dataSource())
    @Bean fun bikeDao() = BikeDao(jdbcTemplate(), namedParamJdbcTemplate())

    
}
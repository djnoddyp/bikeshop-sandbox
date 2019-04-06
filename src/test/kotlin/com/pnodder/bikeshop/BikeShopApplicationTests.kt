package com.pnodder.bikeshop

import com.pnodder.bikeshop.dao.BikeDao
import com.pnodder.bikeshop.domain.Bike
import com.pnodder.bikeshop.enums.Colour
import com.pnodder.bikeshop.enums.Style
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.TestPropertySource
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.jdbc.JdbcTestUtils
import java.time.LocalDateTime

@ExtendWith(SpringExtension::class)
@ContextConfiguration(classes = [BikeShopApplicationTestConfig::class])
@ActiveProfiles("dev")
@TestPropertySource("/test.properties")
/**
 * Uses an embedded h2 data source for dao tests
 */
class BikeShopApplicationTests {

    @Autowired
    lateinit var jdbcTemplate: JdbcTemplate
    @Autowired
    lateinit var namedParamJdbcTemplate: NamedParameterJdbcTemplate
    @Autowired
    lateinit var bikeDao: BikeDao

    private val timestamp = LocalDateTime.now().toString()
    private val log = LoggerFactory.getLogger(BikeShopApplicationTests::class.java)

    @BeforeEach
    fun clearDb() {
        JdbcTestUtils.deleteFromTables(jdbcTemplate, "bike")
    }

    @Test
    fun contextLoads() {
    }

    @Test
    fun testInsertBike() {
        val b1 = Bike(1, "GT", "Tequesta", Colour.BLACK, Style.MOUNTAIN_HARD_TAIL, 799.99, 1)
        val b2 = Bike(2, "Specialized", "Rockhopper", Colour.BLACK, Style.MOUNTAIN_HARD_TAIL, 799.99, 1)
        val bikes = arrayListOf<Bike>(b1, b2)
        assertEquals(2, bikeDao.insertBatch(bikes).get(0).size)
        assertEquals(1, bikeDao.insert(b1))
    }

    @Test
    fun testSelectBike() {
        log.info("Inserting a bike")
        val b1 = Bike(1, "GT", "Tequesta", Colour.BLACK, Style.MOUNTAIN_HARD_TAIL, 799.99, 1)
        bikeDao.insert(b1)
        log.info("Bike: {} {} inserted", b1.make, b1.model)
        assertEquals(1, JdbcTestUtils.countRowsInTable(jdbcTemplate, "bike"))
    }

}


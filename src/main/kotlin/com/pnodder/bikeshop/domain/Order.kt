package com.pnodder.bikeshop.domain

import java.time.LocalDate

data class Order(
        override val id: Int,
        val customer: Customer,
        val cus_id: Int,
        val bikeId: Int,
        val orderDate: LocalDate,
        val totalPrice: Double = 0.0
) : BaseEntity
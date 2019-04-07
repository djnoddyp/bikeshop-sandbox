package com.pnodder.bikeshop.domain

import java.time.LocalDate

data class Order(
        override val id: Int,
        val customerId: Int,
        val bikeId: Int,
        val orderDate: LocalDate,
        val totalPrice: Double = 0.0
) : BaseEntity
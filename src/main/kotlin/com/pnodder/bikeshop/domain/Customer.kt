package com.pnodder.bikeshop.domain

import java.time.LocalDate

data class Customer(
        override val id: Int,
        val firstName: String,
        val lastName: String,
        val email: String,
        val orders: List<Order>,
        val createdOn: LocalDate
) : BaseEntity
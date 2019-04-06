package com.pnodder.bikeshop.domain

import java.time.LocalDate

data class Repair(
        override val id: Int,
        val customerId: Int,
        val repairItemId: Int,
        val description: String,
        val repairDate: LocalDate,
        val cost: Double,
        val employeeId: Int
) : BaseEntity
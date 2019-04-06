package com.pnodder.bikeshop.domain

data class Employee(
        override val id: Int,
        val firstName: String,
        val lastName: String
) : BaseEntity
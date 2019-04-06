package com.pnodder.bikeshop.domain

import com.pnodder.bikeshop.enums.Colour

data class RepairItem(
        override val id: Int,
        val customerId: Int,
        val make: String,
        val model: String,
        val colour: Colour,
        val description: String
) : BaseEntity
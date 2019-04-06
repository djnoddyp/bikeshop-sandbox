package com.pnodder.bikeshop.domain

import com.pnodder.bikeshop.enums.Colour
import com.pnodder.bikeshop.enums.Style

data class Bike(
        override val id: Int,
        val make: String,
        val model: String,
        val colour: Colour,
        val style: Style,
        val price: Double,
        val stock: Int
) : BaseEntity

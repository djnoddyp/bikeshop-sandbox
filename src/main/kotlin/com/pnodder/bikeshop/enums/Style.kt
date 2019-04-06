package com.pnodder.bikeshop.enums

enum class Style(val hasSupension: Boolean) {

    ROAD(false),
    MOUNTAIN_HARD_TAIL(true),
    MOUNTAIN_FULL_SUS(true),
    HYBRID(true);

}
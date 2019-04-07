package com.pnodder.bikeshop.controllers

import com.pnodder.bikeshop.domain.Bike
import com.pnodder.bikeshop.services.BikeService
import org.springframework.web.bind.annotation.*

@RestController
@CrossOrigin
class BikeController(private val bikeService: BikeService) {

    @PostMapping("/bikes")
    fun newBike(@RequestBody bike: Bike) = bikeService.save(bike)

    @GetMapping("/bikes")
    fun all() = bikeService.findAll()

    @GetMapping("/bikes/{id}")
    fun bike(@PathVariable id: Int) = bikeService.findById(id)

}
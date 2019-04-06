package com.pnodder.bikeshop.services

import com.pnodder.bikeshop.dao.BikeDao
import com.pnodder.bikeshop.domain.Bike
import org.springframework.stereotype.Service

@Service
class BikeService(private val bikeDao: BikeDao) {

    fun save(bike: Bike) = bikeDao.insert(bike)

    fun saveAll(bikes: List<Bike>) = bikeDao.insertAll(bikes)

    fun findAll(): MutableIterable<Bike> = bikeDao.findAll()

    fun findById(id: Int) = bikeDao.findById(id)

    fun findByMake(make: String) = bikeDao.findByMake(make)

    fun findModelsByMake(make: String): List<String> {
        val bikes = findByMake(make)
        return bikes.map {b -> b.model}
    }

}
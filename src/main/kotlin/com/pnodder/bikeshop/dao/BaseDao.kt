package com.pnodder.bikeshop.dao

interface BaseDao<BaseEntity> {

    fun findById(id: Int): BaseEntity?

    fun findAll(): List<BaseEntity>?

    fun insert(obj: BaseEntity): Int
    
    fun insertAll(objs: List<BaseEntity>): Int
    
    fun insertBatch(batch: List<BaseEntity>): Array<out IntArray>
    
    fun delete(obj: BaseEntity): Int
    
    fun update(obj: BaseEntity): Int

    fun count(): Int?
    
}
package com.elena.escuela.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.elena.escuela.db.DbEntity

@Dao

interface DbDao {
    @Query("SELECT * FROM DbEntity")
    fun getAll(): List<DbEntity>

    @Query("SELECT * FROM DbEntity")
    fun getAllLive(): LiveData<List<DbEntity>>

    @Insert
    fun insert(dbStatus: DbEntity)

    @Delete
    fun delete(dbStatus: DbEntity)




}
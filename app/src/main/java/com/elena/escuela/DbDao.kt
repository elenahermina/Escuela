package com.elena.escuela

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface DbDao {
    @Insert
    fun insert(dbEntity: DbEntity)

    @Update
    fun update(dbEntity: DbEntity)

    @Insert
    fun insertAll(dbEntity: List< DbEntity >)

    @Delete
    fun delete(dbEntity: DbEntity)

    @Query("SELECT * FROM DbEntity")

    fun getAll(): List<DbEntity>
    @Query("SELECT * FROM DbEntity")

    fun getAllLive(): LiveData<List<DbEntity>>


}
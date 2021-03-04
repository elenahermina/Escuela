package com.elena.escuela

import androidx.room.*

@Dao
interface RegisteredUserDao {
    @Insert
    fun insert(RegisteredUser: RegisteredUser)

    @Update
    fun update(RegisteredUser:RegisteredUser)

    @Insert
    fun insertAll(RegisteredUser: List<RegisteredUser>)

    @Delete
    fun delete(RegisteredUser: RegisteredUser)

    @Query("SELECT * FROM RegisteredUser")
    fun getAll(): List<RegisteredUser>
}
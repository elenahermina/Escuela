package com.elena.escuela.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.elena.escuela.student.RegisteredUser

@Dao
interface RegisteredUserDao {

    @Query("SELECT * FROM RegisteredUser")
    fun getAllLive(): LiveData<List<RegisteredUser>>

    @Query("SELECT * FROM RegisteredUser")
    fun getAll(): List<RegisteredUser>

    @Insert
    fun insert(registeredUser: RegisteredUser)

    @Delete
    fun delete(registeredUser: RegisteredUser)
}
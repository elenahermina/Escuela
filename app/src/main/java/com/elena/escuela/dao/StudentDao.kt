package com.elena.escuela.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.elena.escuela.student.Student

@Dao
interface StudentDao{

    @Query("SELECT * FROM Student")
    fun getAll(): List<Student>

    @Query("SELECT * FROM Student")
    fun getAllLive(): LiveData<List<Student>>

    @Insert
    fun insert(student: Student)

    @Delete
    fun delete(student: Student)
}
package com.elena.escuela

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface StudentDao{

    @Query("SELECT * FROM Student")
    fun getAll(): List<Student>

    @Query("SELECT * FROM Student")
    fun getAllLive(): LiveData<List<Student>>

    @Query("SELECT * FROM Student WHERE id IN (:StudentsId )")
    fun loadAllByIds(StudentsId: IntArray): List<Student>



    @Insert
    fun insert(Student: Student)

    @Update
    fun update(Student: Student)

    @Insert
    fun insertAll(Students: List<Student>)

    @Delete
    fun delete(Student: Student)
}
package com.elena.escuela

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.elena.escuela.student.Student

@Dao
interface BootcampDao {
    @Query("SELECT * FROM Bootcamp")
    fun getAll(): List<Bootcamp>

    @Query("SELECT * FROM Bootcamp")
    fun getAllLive(): LiveData<List<Bootcamp>>

    @Insert
    fun insert(bootcamp: Bootcamp)

    @Insert
    fun insertAll(listBootcamp: List<Bootcamp>)


}
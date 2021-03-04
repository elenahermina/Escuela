package com.elena.escuela

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
class Student (var email: String, var nombre :  String, var apellido : String?, var foto: Int  ) {

    @PrimaryKey(autoGenerate = true)
    var id : Int = 0

}
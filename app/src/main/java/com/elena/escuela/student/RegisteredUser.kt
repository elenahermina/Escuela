package com.elena.escuela.student

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity

class RegisteredUser (var email : String ) {

    @PrimaryKey(autoGenerate = true)
    var id : Int = 0
}
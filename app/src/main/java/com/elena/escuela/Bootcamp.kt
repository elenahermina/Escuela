package com.elena.escuela

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Bootcamp(val bootcampName: String, val about: String) {

    @PrimaryKey(autoGenerate = true)
    var bootcampId = 0
}
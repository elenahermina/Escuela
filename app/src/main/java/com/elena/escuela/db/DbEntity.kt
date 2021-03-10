package com.elena.escuela.db

import androidx.room.Dao
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
  data class DbEntity (@PrimaryKey val id : Int, var created : Boolean) {



}
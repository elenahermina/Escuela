package com.elena.escuela.student

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
class Student (var email: String, var nombre :  String, var apellido : String?,val photoId: Int?, @ColumnInfo(typeAffinity = ColumnInfo.BLOB) var image: ByteArray? = null, var imageOnline: String? = null){
    @PrimaryKey(autoGenerate = true)
    var id : Int = 0 }
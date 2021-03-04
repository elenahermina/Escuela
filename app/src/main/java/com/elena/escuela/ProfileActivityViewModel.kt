package com.elena.escuela

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import kotlinx.coroutines.delay

class ProfileActivityViewModel(application: Application): AndroidViewModel(application) {
    private var myself = Student("", "Yo", "barbullushi", R.mipmap.ic_launcher)

    suspend fun getListaAlumnos(): List<Student>{

        val alumno1 = Student("oliver@gmail.com", "Oliver", "Sanchez", R.drawable.avatar_1)

        val alumno2 = Student("ana@gmail.com", "Ana", "Lopez", R.drawable.avatar_11)

        val alumno3 = Student("isabel@gmail.com", "Isabel", "Blanco", R.drawable.avatar_17)

        val alumno4 = Student("dana@gmail.com", "Dana", "Negro", R.drawable.avatar_5)

        val alumno5 =   Student("jose@gmail.com", "Jose", "Garcia", R.drawable.avatar_7)


        return listOf(myself, alumno1, alumno2, alumno3, alumno4,alumno5)

    }
    fun setMyselfEmail(email: String) {
        myself.email = email
    }

    suspend  fun getAlumno (student : Student): Student {
        delay(1000)
        return student
    }
}
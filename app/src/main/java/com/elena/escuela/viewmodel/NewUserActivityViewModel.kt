package com.elena.escuela.viewmodel

import android.app.Application
import android.graphics.Bitmap
import androidx.lifecycle.AndroidViewModel
import com.elena.escuela.db.Db
import com.elena.escuela.student.Student
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.ByteArrayOutputStream

class NewUserActivityViewModel(application: Application): AndroidViewModel(application) {

   suspend fun insertarUsuario(name: String, surename: String, email: String, bitmap: Bitmap?) {
        withContext(Dispatchers.IO) {

            var student = Student(email, name, surename, 0)
            bitmap?.let {
                val stream = ByteArrayOutputStream()
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream)
                student.image = stream.toByteArray()
            }

            Db.getDatabase(getApplication()).studentsDao().insert(student)
            }
        }
    }


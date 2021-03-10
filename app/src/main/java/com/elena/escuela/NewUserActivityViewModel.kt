package com.elena.escuela

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class NewUserActivityViewModel(application: Application): AndroidViewModel(application) {

 fun insertarUsuario(name: String, apellido: String, email: String, foto: Int){


     var student = Student(email, name, apellido, foto)




     viewModelScope.launch {
    Db.getDatabase(getApplication()).studentsDao().insert(student)
 }
}
}

package com.elena.escuela.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.elena.escuela.db.Db
import com.elena.escuela.student.Student
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ProfileActivityViewModel(application: Application): AndroidViewModel(application) {

    val userList : MutableLiveData<List<Student>> = MutableLiveData()


    fun getAllUser(){
        viewModelScope.launch(Dispatchers.IO) {
            val result = Db.getDatabase(getApplication()).studentsDao().getAll()
            withContext(Dispatchers.Main) {
                userList.value = result
            }
        }
    }
}
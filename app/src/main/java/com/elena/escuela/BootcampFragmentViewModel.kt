package com.elena.escuela

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

 class BootcampFragmentViewModel(application: Application) : AndroidViewModel(application) {
 val bootcampList : MutableLiveData<List<Bootcamp>> = MutableLiveData()

    suspend fun getBootcamp() {
        viewModelScope.launch(Dispatchers.IO) {
           // changeBootcampListValueOnUi(Db.getDatabase(getApplication()).bootcampsDao().getAll())
        }
    }

   /* private suspend fun changeBootcampListValueOnUi(studentList: List<Student>) = withContext(Dispatchers.Main) {
        bootcampList.value = bootcampList
    }*/
}
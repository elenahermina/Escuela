package com.elena.escuela.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.elena.escuela.db.Db
import com.elena.escuela.request.GetUserRequest
import com.elena.escuela.student.Student
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ProfileFragmentViewModel(application: Application): AndroidViewModel(application) {

    val userList : MutableLiveData<List<Student>> = MutableLiveData()
    val status : MutableLiveData<DownloadStatus> = MutableLiveData()

    enum class DownloadStatus {
        FINISHED, STARTED
    }


    fun getAllUser(){
        viewModelScope.launch(Dispatchers.IO) {
            changeUserListValueOnUi(Db.getDatabase(getApplication()).studentsDao().getAll())
        }
    }

    fun downloadUser(){
        viewModelScope.launch(Dispatchers.IO) {
            changeDownloadStatusOnUi(DownloadStatus.STARTED)
            Db.getDatabase(getApplication()).studentsDao().insert(GetUserRequest.send())
            changeUserListValueOnUi(Db.getDatabase(getApplication()).studentsDao().getAll())
            changeDownloadStatusOnUi(DownloadStatus.FINISHED)
        }

        }

    private suspend fun changeDownloadStatusOnUi(downloadStatus: DownloadStatus) = withContext(Dispatchers.Main) {
        status.value = downloadStatus
    }

    private suspend fun changeUserListValueOnUi(studentList: List<Student>) = withContext(Dispatchers.Main) {
        userList.value = studentList
    }

    fun deleteStudent(student: Student) {
        viewModelScope.launch(Dispatchers.IO) {
            Db.getDatabase(getApplication()).studentsDao().delete(student)
            changeUserListValueOnUi(Db.getDatabase(getApplication()).studentsDao().getAll())
        }
    }

}

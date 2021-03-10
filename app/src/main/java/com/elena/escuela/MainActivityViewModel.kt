package com.elena.escuela

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class MainActivityViewModel (application: Application) : AndroidViewModel(application) {

    companion object {
        const val USER_NAME = "USER_NAME"
    }
    /*suspend fun isStudentValid(email:String): Boolean {
        val resultado = viewModelScope.async(Dispatchers.IO) {
            val listUserDB = Db.getDatabase(getApplication()).registeredUserDao().getAll()
            listUserDB.forEach {
                if (it.email.contentEquals(email)) {
                    return@async true

            }
            }
            return@async false
        }
        return resultado.await()
     }*/

    fun isStudentValid(email: String) : Boolean {
        return email.contains("@") && email.contains(".")
    }

    fun insertNewUser(email:String) {
        viewModelScope.launch {
            val registeredUSer = RegisteredUser(email)
            Db.getDatabase(getApplication()).registeredUserDao().insert(registeredUSer)
        }
    }


     fun cargarPreferencias() : String? {
        val sharedPref = getApplication<App>().getSharedPreferences("Preference", Context.MODE_PRIVATE)
        return sharedPref.getString(MainActivityViewModel.USER_NAME, "")
    }

      fun guardarPreferencias(string : String) {
        val sharedPref =  getApplication<App>().getSharedPreferences("Preference", Context.MODE_PRIVATE)
        with (sharedPref.edit()) {
            putString(MainActivityViewModel.USER_NAME, string)
            commit()
        }
    }

    suspend fun isEmailRegistered(email: String): Boolean {
        val resultado = viewModelScope.async(Dispatchers.IO) {
            val listUserDB = Db.getDatabase(getApplication()).registeredUserDao().getAll()
            listUserDB.forEach {
                if (it.email.contentEquals(email)) {
                    return@async true

                }
            }
            return@async false
        }
        return resultado.await()
    }

}
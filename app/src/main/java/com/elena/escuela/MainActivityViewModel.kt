package com.elena.escuela

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.async

class MainActivityViewModel (application: Application) : AndroidViewModel(application) {

    companion object {
        const val USER_NAME = "USER_NAME"
    }
    suspend fun isStudentValid(email:String): Boolean {
        var name = "elena@neoland.com"
        var index = 0
        var resultado = viewModelScope.async {
            val listUserDB = Db.getDatabase(getApplication()).registeredUserDao().getAll()
            listUserDB.forEach {
                if (it.email.contentEquals(name)) {
                    return@async true

            }
            }
            return@async false
        }
        return resultado.await()

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

}
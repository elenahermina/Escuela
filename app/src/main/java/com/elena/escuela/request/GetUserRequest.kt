package com.elena.escuela.request

import android.util.Log
import com.elena.escuela.request.model.StudentApi
import com.elena.escuela.request.model.StudentApiMap
import com.elena.escuela.student.Student
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONObject

class GetUserRequest {

    companion object {
        private val URL = "https://randomuser.me/api/?results=10"

        suspend fun send() : List<Student> {
            val client = OkHttpClient()
            val request = Request.Builder().url(URL).build()
            return withContext(Dispatchers.IO) {
                val response = client.newCall(request).execute()
                response.body?.string()?.let {
                    val jsonObject = JSONObject(it)
                    val results = jsonObject.optJSONArray("results")
                    results?.let {
                        Log.w("GetAllStudents", results.toString())
                        val gson = Gson()
                        val itemType = object : TypeToken<List<StudentApi>>() {}.type
                        val list = gson.fromJson<List<StudentApi>>(results.toString(), itemType)
                        return@withContext StudentApiMap.mapToStudentList(list)
                    }
                }
                return@withContext emptyList()
            }
        }
    }

}
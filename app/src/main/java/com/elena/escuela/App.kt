package com.elena.escuela

import android.app.Application
import com.elena.escuela.db.Db

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        Db.getDatabase(this)
    }

}
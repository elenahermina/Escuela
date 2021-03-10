package com.elena.escuela.splash

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.elena.escuela.db.Db
import com.elena.escuela.db.DbEntity

class SplashActivityViewModel(application: Application): AndroidViewModel(application) {

    val status : LiveData<List<DbEntity>> = liveData { emitSource(Db.getDatabase(application).dbDao().getAllLive()) }
}
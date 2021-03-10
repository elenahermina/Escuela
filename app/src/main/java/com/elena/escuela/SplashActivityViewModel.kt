package com.elena.escuela

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData

class SplashActivityViewModel(application: Application): AndroidViewModel(application) {

    val status : LiveData<List<DbEntity>> = liveData { emitSource(Db.getDatabase(application).dbDao().getAllLive()) }
}
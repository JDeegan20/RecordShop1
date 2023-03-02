package org.wit.recordshop.main

import android.app.Application
import org.wit.recordshop.models.RecordModel
import timber.log.Timber
import timber.log.Timber.i

class MainApp : Application() {

    val records = ArrayList<RecordModel>()

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
        i("Record Shop started")
    }
}
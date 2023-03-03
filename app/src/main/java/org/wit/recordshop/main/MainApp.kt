package org.wit.recordshop.main

import android.app.Application
import org.wit.recordshop.models.RecordMemStore
import org.wit.recordshop.models.RecordModel
import timber.log.Timber
import timber.log.Timber.i

class MainApp : Application() {

    val records = RecordMemStore()

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
        i("Record shop started")

    }
}
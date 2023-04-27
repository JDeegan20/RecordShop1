package org.wit.recordshop.main

import android.app.Application
import org.wit.recordshop.models.RecordMemStore
import org.wit.recordshop.models.RecordJSONStore
import org.wit.recordshop.models.RecordStore
import timber.log.Timber
import timber.log.Timber.i

class MainApp : Application() {

    lateinit var records: RecordStore

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
       records = RecordJSONStore(applicationContext)
        i("Record shop started")
    }
}
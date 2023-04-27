package org.wit.recordshop.main

import android.app.Application
import org.wit.recordshop.models.RecordMemStore
import org.wit.recordshop.models.RecordModel
import org.wit.recordshop.models.RecordStore
import timber.log.Timber
import timber.log.Timber.i

class MainApp : Application() {

    lateinit var records: RecordStore
    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
        records = RecordMemStore()
        i("Record shop started")
    }
}
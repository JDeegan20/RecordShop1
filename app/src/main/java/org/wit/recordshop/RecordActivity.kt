package org.wit.recordshop

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import timber.log.Timber
import timber.log.Timber.i

class RecordActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        Timber.plant(Timber.DebugTree())
        i("Placemark Activity started..")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_record)
    }
}


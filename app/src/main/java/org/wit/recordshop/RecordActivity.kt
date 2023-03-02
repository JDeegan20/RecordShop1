package org.wit.recordshop

import org.wit.recordshop.databinding.ActivityRecordBinding


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import timber.log.Timber
import timber.log.Timber.i

class RecordActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRecordBinding
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_record)

        Timber.plant(Timber.DebugTree())
        i("Placemark Activity started..")

        
        binding = ActivityRecordBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.btnAdd.setOnClickListener() {
            i("add Button Pressed")
        }
    }
}


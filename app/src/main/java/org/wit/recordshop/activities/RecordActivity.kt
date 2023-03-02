package org.wit.recordshop.activities

import org.wit.recordshop.databinding.ActivityRecordBinding


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import org.wit.recordshop.R
import org.wit.recordshop.main.MainApp
import org.wit.recordshop.models.RecordModel
//import timber.log.Timber
import timber.log.Timber.i

class RecordActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRecordBinding
    var record = RecordModel()
    lateinit var app: MainApp

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_record)

        binding = ActivityRecordBinding.inflate(layoutInflater)
        setContentView(binding.root)

        app = application as MainApp
        i("Record Activity started...")

        binding.btnAdd.setOnClickListener() {
            record.title = binding.recordTitle.text.toString()
            record.description = binding.description.text.toString()
            record.genre = binding.genre.text.toString()
            if (record.title.isNotEmpty()) {
                app!!.records.add(record.copy())
                i("add Button Pressed: ${record}")
                for (i in app!!.records.indices)
                { i("Record[$i]:${this.app!!.records[i]}") }
            } else {
                Snackbar.make(it, "Please Enter a record title", Snackbar.LENGTH_LONG)
                    .show()
            }
        }
    }
}


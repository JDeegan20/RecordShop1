package org.wit.recordshop.activities

import org.wit.recordshop.databinding.ActivityRecordBinding


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
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

        binding.toolbarAdd.title = title
        setSupportActionBar(binding.toolbarAdd)

        app = application as MainApp
        if (intent.hasExtra("record_edit")) {
           record = intent.extras?.getParcelable("record_edit")!!
            binding.recordTitle.setText(record.title)
            binding.description.setText(record.description)
            binding.genre.setText(record.genre)
        }

        binding.btnAdd.setOnClickListener() {
            record.title = binding.recordTitle.text.toString()
            record.description = binding.description.text.toString()
            record.genre = binding.genre.text.toString()
            if (record.title.isNotEmpty()) {
                app.records.create(record.copy())
                setResult(RESULT_OK)
                finish()
            } else {
                Snackbar.make(it, "Please Enter a title", Snackbar.LENGTH_LONG)
                    .show()
            }

        }
    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_record, menu)
        return super.onCreateOptionsMenu(menu)
    }
}




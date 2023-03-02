package org.wit.recordshop.activities

import org.wit.recordshop.databinding.ActivityRecordBinding


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import org.wit.recordshop.R
import org.wit.recordshop.models.RecordModel
import timber.log.Timber
import timber.log.Timber.i

class RecordActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRecordBinding
    var record = RecordModel()
    val records = ArrayList<RecordModel>()

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_record)

        Timber.plant(Timber.DebugTree())
        i("Record Activity started..")


        binding = ActivityRecordBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.btnAdd.setOnClickListener() {
            record.title = binding.recordTitle.text.toString()
            record.description = binding.description.text.toString()
            record.genre = binding.genre.text.toString()
            if (record.title.isNotEmpty()) {
                records.add(record.copy())

                i("add Button Pressed: ${record}")
                for (i in records.indices) {
                    i("Record[$i]:${this.records[i]}")
                }
            } else {
                Snackbar.make(it, "Please Enter a title", Snackbar.LENGTH_LONG)
                    .show()
            }
        }
    }
}


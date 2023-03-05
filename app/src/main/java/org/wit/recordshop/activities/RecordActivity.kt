package org.wit.recordshop.activities

import org.wit.recordshop.databinding.ActivityRecordBinding


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
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
    var edit = false


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_record)

        binding = ActivityRecordBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.toolbarAdd.title = title
        setSupportActionBar(binding.toolbarAdd)

        app = application as MainApp
        if (intent.hasExtra("record_edit")) {
            edit = true
           record = intent.extras?.getParcelable("record_edit")!!
            binding.recordTitle.setText(record.title)
            binding.description.setText(record.description)
            binding.genre.setText(record.genre)
            binding.btnAdd.setText(R.string.save_record)
        }


        binding.btnAdd.setOnClickListener() {
            record.title = binding.recordTitle.text.toString()
            record.description = binding.description.text.toString()
            record.genre = binding.genre.text.toString()
            if (record.title.isEmpty()) {
                Snackbar.make(it,R.string.enter_record_title, Snackbar.LENGTH_LONG)
                    .show()
            } else {
                if (edit) {
                    app.records.update(record.copy())
                } else {
                    app.records.create(record.copy())
                }
            }
            setResult(RESULT_OK)
            finish()
        }

        binding.chooseImage.setOnClickListener {
            i("Select an image")
        }


    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.item_cancel -> {
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
}




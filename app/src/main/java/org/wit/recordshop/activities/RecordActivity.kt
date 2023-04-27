package org.wit.recordshop.activities

import android.content.Intent
import org.wit.recordshop.databinding.ActivityRecordBinding


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.google.android.material.snackbar.Snackbar
import com.squareup.picasso.Picasso
import org.wit.placemark.helpers.showImagePicker
import org.wit.recordshop.R
import org.wit.recordshop.main.MainApp
import org.wit.recordshop.models.Location
import org.wit.recordshop.models.RecordModel
//import timber.log.Timber
import timber.log.Timber.i

class RecordActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRecordBinding
    private lateinit var imageIntentLauncher: ActivityResultLauncher<Intent>
    private lateinit var mapIntentLauncher : ActivityResultLauncher<Intent>



    var record = RecordModel()
    lateinit var app: MainApp
    var edit = false

    //var location = Location(52.245696, -7.139102, 15f)

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
                Snackbar.make(it, R.string.enter_record_title, Snackbar.LENGTH_LONG)
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



        binding.recordLocation.setOnClickListener {
            val location = Location(52.245696, -7.139102, 15f)
            if (record.zoom != 0f) {
                location.lat =  record.lat
                location.lng = record.lng
                location.zoom = record.zoom
            }
            val launcherIntent = Intent(this, MapActivity::class.java)
                .putExtra("location", location)
            mapIntentLauncher.launch(launcherIntent)
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

    private fun registerImagePickerCallback() {
        imageIntentLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult())
            { result ->
                when(result.resultCode){
                    RESULT_OK -> {
                        if (result.data != null) {
                            i("Got Result ${result.data!!.data}")
                            record.image = result.data!!.data!!
                            Picasso.get()
                                .load(record.image)
                                .into(binding.recordImage)
                        } // end of if
                    }
                    RESULT_CANCELED -> { } else -> { }
                }
            }
    }


    private fun registerMapCallback() {
        mapIntentLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult())
            { result ->
                when (result.resultCode) {
                    RESULT_OK -> {
                        if (result.data != null) {
                            i("Got Location ${result.data.toString()}")
                            val location = result.data!!.extras?.getParcelable<Location>("location")!!
                            i("Location == $location")
                           record.lat = location.lat
                            record.lng = location.lng
                            record.zoom = location.zoom
                        } // end of if
                    }
                    RESULT_CANCELED -> { } else -> { }
                }
            }
    }


}




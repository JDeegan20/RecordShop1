package org.wit.recordshop.activities

import android.app.Activity.RESULT_OK
import android.content.Intent
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import org.wit.recordshop.databinding.ActivityRecordBinding
import org.wit.recordshop.helpers.showImagePicker
import org.wit.recordshop.main.MainApp
import org.wit.recordshop.models.Location
import org.wit.recordshop.models.RecordModel
import timber.log.Timber

class RecordPresenter(private val view: RecordView) {

    var record = RecordModel()
    var app: MainApp = view.application as MainApp
    var binding: ActivityRecordBinding = ActivityRecordBinding.inflate(view.layoutInflater)
    private lateinit var imageIntentLauncher : ActivityResultLauncher<Intent>
    private lateinit var mapIntentLauncher : ActivityResultLauncher<Intent>
    var edit = false;



    init {
        if (view.intent.hasExtra("record_edit")) {
            edit = true
            record = view.intent.extras?.getParcelable("record_edit")!!
            view.showRecord(record)
        }        registerImagePickerCallback()
        registerMapCallback()
    }
    fun doAddOrSave(title: String, description: String, genre: String) {
        record.title = title
        record.description = description
        record.genre = genre
        if (edit) {
            app.records.update(record)
        } else {
            app.records.create(record)
        }        view.setResult(RESULT_OK)
        view.finish()
    }
    fun doCancel() {
        view.finish()
    }
    fun doDelete() {
        view.setResult(99)
        app.records.delete(record)
        view.finish()
    }
    fun doSelectImage() {
        showImagePicker(imageIntentLauncher,view)
    }
    fun doSetLocation() {
        val location = Location(52.245696, -7.139102, 15f)
        if (record.zoom != 0f) {
            location.lat =  record.lat
            location.lng = record.lng
            location.zoom = record.zoom
        }
        val launcherIntent = Intent(view,EditLocationView::class.java)
            .putExtra("location", location)
        mapIntentLauncher.launch(launcherIntent)

    }
    fun cacheRecord (title: String, description: String, genre: String) {
        record.title = title;
        record.description = description;
        record.genre = genre;
    }

    private fun registerImagePickerCallback() {
        imageIntentLauncher =
            view.registerForActivityResult(ActivityResultContracts.StartActivityForResult())
            { result ->
                when(result.resultCode){
                    AppCompatActivity.RESULT_OK -> {
                        if (result.data != null) {
                            Timber.i("Got Result ${result.data!!.data}")
                            record.image = result.data!!.data!!
                            view.contentResolver.takePersistableUriPermission(record.image,
                                Intent.FLAG_GRANT_READ_URI_PERMISSION)
                            view.updateImage(record.image)
                        } // end of if
                    }
                    AppCompatActivity.RESULT_CANCELED -> { } else -> { }
                }            }    }
    private fun registerMapCallback() {
        mapIntentLauncher =
            view.registerForActivityResult(ActivityResultContracts.StartActivityForResult())
            { result ->
                when (result.resultCode) {
                    AppCompatActivity.RESULT_OK -> {
                        if (result.data != null) {
                            Timber.i("Got Location ${result.data.toString()}")
                            val location = result.data!!.extras?.getParcelable<Location>("location")!!
                            Timber.i("Location == $location")
                            record.lat = location.lat
                            record.lng = location.lng
                           record.zoom = location.zoom
                        }
                    }
                    AppCompatActivity.RESULT_CANCELED -> { } else -> { }
                }            }    }}

package org.wit.recordshop.activities

import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.squareup.picasso.Picasso
import org.wit.recordshop.R
import org.wit.recordshop.databinding.ActivityRecordBinding
import org.wit.recordshop.models.RecordModel
import timber.log.Timber.i

class RecordView : AppCompatActivity() {

    private lateinit var binding: ActivityRecordBinding
    private lateinit var presenter: RecordPresenter
    var record = RecordModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        binding = ActivityRecordBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.toolbarAdd.title = title
        setSupportActionBar(binding.toolbarAdd)

        presenter = RecordPresenter(this)

        binding.chooseImage.setOnClickListener {
            presenter.cacheRecord(binding.recordTitle.text.toString(), binding.description.text.toString(), binding.genre.text.toString())
            presenter.doSelectImage()
        }

        binding.recordLocation.setOnClickListener {
            presenter.cacheRecord(binding.recordTitle.text.toString(), binding.description.text.toString(), binding.genre.text.toString())
            presenter.doSetLocation()
        }

        binding.btnAdd.setOnClickListener {
            if (binding.recordTitle.text.toString().isEmpty()) {
                Snackbar.make(binding.root, R.string.enter_record_title, Snackbar.LENGTH_LONG)
                    .show()
            } else {

                presenter.doAddOrSave(binding.recordTitle.text.toString(), binding.description.text.toString(), binding.genre.text.toString())
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_record, menu)
        val deleteMenu: MenuItem = menu.findItem(R.id.item_delete)
        deleteMenu.isVisible = presenter.edit
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.item_delete -> {
                presenter.doDelete()
            }
            R.id.item_cancel -> {
                presenter.doCancel()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    fun showRecord(record: RecordModel) {
        binding.recordTitle.setText(record.title)
        binding.description.setText(record.description)
        binding.genre.setText(record.genre)
        binding.btnAdd.setText(R.string.save_record)
        Picasso.get()
            .load(record.image)
            .into(binding.recordImage)
        if (record.image != Uri.EMPTY) {
            binding.chooseImage.setText(R.string.change_record_image)
        }
    }

    fun updateImage(image: Uri) {
        i("Image updated")
        Picasso.get()
            .load(image)
            .into(binding.recordImage)
        binding.chooseImage.setText(R.string.change_record_image)
    }
}




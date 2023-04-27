package org.wit.recordshop.activities

import android.app.Activity
import android.content.Intent
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import org.wit.recordshop.main.MainApp
import org.wit.recordshop.models.RecordModel

class RecordListPresenter(val view: RecordListView) {

    var app: MainApp
    private lateinit var refreshIntentLauncher : ActivityResultLauncher<Intent>
    private lateinit var mapIntentLauncher : ActivityResultLauncher<Intent>
    private var position: Int = 0

    init {
        app = view.application as MainApp
        registerMapCallback()
        registerRefreshCallback()
    }

    fun getRecords() = app.records.findAll()

    fun doAddRecord() {
        val launcherIntent = Intent(view, RecordView::class.java)
        refreshIntentLauncher.launch(launcherIntent)
    }

    fun doEditRecord(record: RecordModel, pos: Int) {
        val launcherIntent = Intent(view, RecordView::class.java)
        launcherIntent.putExtra("record_edit", record)
        position = pos
        refreshIntentLauncher.launch(launcherIntent)
    }

    fun doShowRecordsMap() {
        val launcherIntent = Intent(view, RecordMapsActivity::class.java)
        mapIntentLauncher.launch(launcherIntent)
    }

    private fun registerRefreshCallback() {
        refreshIntentLauncher =
            view.registerForActivityResult(
                ActivityResultContracts.StartActivityForResult()
            ) {
                if (it.resultCode == Activity.RESULT_OK) view.onRefresh()
                else // Deleting
                    if (it.resultCode == 99) view.onDelete(position)
            }
    }
    private fun registerMapCallback() {
        mapIntentLauncher = view.registerForActivityResult(ActivityResultContracts.StartActivityForResult())
        {  }
    }
}

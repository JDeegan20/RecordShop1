package org.wit.recordshop.activities

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import org.wit.recordshop.R
import org.wit.recordshop.adapters.RecordAdapter
import org.wit.recordshop.databinding.ActivityRecordListBinding
import org.wit.recordshop.main.MainApp
import org.wit.recordshop.models.RecordModel
import org.wit.recordshop.adapters.RecordListener

class RecordListView : AppCompatActivity(), RecordListener {

    lateinit var app: MainApp
    private lateinit var binding: ActivityRecordListBinding
    lateinit var presenter: RecordListPresenter
    private var position: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecordListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.toolbar.title = title
        setSupportActionBar(binding.toolbar)
        presenter = RecordListPresenter(this)
        app = application as MainApp

        val layoutManager = LinearLayoutManager(this)
        binding.recyclerView.layoutManager = layoutManager
        loadRecords()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.item_add -> { presenter.doAddRecord() }
            R.id.item_map -> { presenter.doShowRecordsMap() }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onRecordClick(record: RecordModel, position: Int) {
        this.position = position
        presenter.doEditRecord(record, this.position)
    }

    private fun loadRecords() {
        binding.recyclerView.adapter = RecordAdapter(presenter.getRecords(), this)
        onRefresh()
    }

    fun onRefresh() {
        binding.recyclerView.adapter?.
        notifyItemRangeChanged(0,presenter.getRecords().size)
    }

    fun onDelete(position : Int) {
        binding.recyclerView.adapter?.notifyItemRemoved(position)
    }
}

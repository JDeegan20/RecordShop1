package org.wit.placemark.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.wit.recordshop.databinding.ActivityRecordListBinding
import org.wit.recordshop.databinding.CardRecordBinding
import org.wit.recordshop.main.MainApp
import org.wit.recordshop.models.RecordModel

class RecordListActivity : AppCompatActivity() {

    lateinit var app: MainApp
    private lateinit var binding: ActivityRecordListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecordListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        app = application as MainApp

        val layoutManager = LinearLayoutManager(this)
        binding.recyclerView.layoutManager = layoutManager
        binding.recyclerView.adapter = RecordAdapter(app.records)
    }
}

class RecordAdapter constructor(private var records: List<RecordModel>) :
    RecyclerView.Adapter<RecordAdapter.MainHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainHolder {
        val binding = CardRecordBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)

        return MainHolder(binding)
    }

    override fun onBindViewHolder(holder: MainHolder, position: Int) {
        val record = records[holder.adapterPosition]
        holder.bind(record)
    }

    override fun getItemCount(): Int = records.size

    class MainHolder(private val binding : CardRecordBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(record: RecordModel) {
            binding.recordTitle.text = record.title
            binding.description.text = record.description
            binding.genre.text = record.genre
        }
    }
}

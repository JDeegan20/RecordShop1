package org.wit.recordshop.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import org.wit.recordshop.databinding.CardRecordBinding
import org.wit.recordshop.models.RecordModel

interface RecordListener {
    fun onRecordClick(record: RecordModel, position : Int)
}

class RecordAdapter constructor(private var records: List<RecordModel>,
                                   private val listener: RecordListener) :
    RecyclerView.Adapter<RecordAdapter.MainHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainHolder {
        val binding = CardRecordBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)

        return MainHolder(binding)
    }

    override fun onBindViewHolder(holder: MainHolder, position: Int) {
        val record = records[holder.adapterPosition]
        holder.bind(record, listener)
    }

    override fun getItemCount(): Int = records.size

    class MainHolder(private val binding : CardRecordBinding) :
        RecyclerView.ViewHolder(binding.root) {



        fun bind(record: RecordModel, listener: RecordListener) {
            binding.recordTitle.text = record.title
            binding.description.text = record.description
            binding.genre.text = record.genre
            Picasso.get().load(record.image).resize(200,200).into(binding.imageIcon)
            binding.root.setOnClickListener { listener.onRecordClick(record,adapterPosition) }
        }





    }
}


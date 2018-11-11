package com.thing.recall.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.thing.recall.R
import com.thing.recall.model.Memory
import kotlinx.android.synthetic.main.memory_item.view.*
import java.text.DateFormat

class MemoryAdapter(val context: Context, val memories: List<Memory>) :
    RecyclerView.Adapter<MemoryAdapter.MemoryViewHolder>() {
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): MemoryViewHolder {
        return MemoryViewHolder(LayoutInflater.from(context).inflate(R.layout.memory_item, p0, false))
    }

    override fun getItemCount(): Int {
        return memories.size
    }

    override fun onBindViewHolder(p0: MemoryViewHolder, p1: Int) {
        p0.setIsRecyclable(false)
        val memory = memories[p1]

        p0.itemView.setOnClickListener {

        }
        p0.dateTextView.text = DateFormat.getDateInstance(DateFormat.SHORT).format(memory.date) //FIXME
        p0.descTextView.text = memory.description
    }

    class MemoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var dateTextView: TextView = itemView.dateTextView
        var descTextView: TextView = itemView.descTextView
    }
}
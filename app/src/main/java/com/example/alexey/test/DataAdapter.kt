package com.example.alexey.test

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.alexey.test.models.Item
import android.widget.TextView
import kotlinx.android.synthetic.main.data_item_layout.view.*


class DataAdapter : RecyclerView.Adapter<DataHolder>()  {

    private var items: Array<Item> = arrayOf()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return DataHolder(layoutInflater.inflate(R.layout.data_item_layout, parent, false))
    }

    override fun getItemCount(): Int=items.size

    override fun onBindViewHolder(holder: DataHolder, index: Int) {
        holder.itemView.nameView.text=items[index].name
        holder.itemView.volumeView.text="${items[index].volume}"
        holder.itemView.amountView.text="%.2f".format(items[index].price.amount)
    }

    fun resetItems(items: Array<Item>){
        this.items=items
        notifyDataSetChanged()
    }

}


class DataHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    init {
        val nameView = itemView.findViewById(R.id.nameView) as TextView
        val volumeView = itemView.findViewById(R.id.volumeView) as TextView
        val amountView = itemView.findViewById(R.id.amountView) as TextView
    }

}


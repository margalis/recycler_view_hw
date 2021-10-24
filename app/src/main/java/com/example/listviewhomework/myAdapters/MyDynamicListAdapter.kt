package com.example.listviewhomework.myAdapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.AsyncDifferConfig
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.listviewhomework.DriverDiffUtilItemCallback
import com.example.listviewhomework.R
import com.example.listviewhomework.driver.Driver

class MyDynamicListAdapter : ListAdapter<Driver, MyDynamicListAdapter.RecyclingViewHolder>(
    AsyncDifferConfig.Builder<Driver>(DriverDiffUtilItemCallback()).build()
){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclingViewHolder {
        return  RecyclingViewHolder(
            LayoutInflater.from(parent.context)
            .inflate(R.layout.item_list_view,parent,false))
    }

    override fun onBindViewHolder(holder: MyDynamicListAdapter.RecyclingViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class RecyclingViewHolder(itemView : View): RecyclerView.ViewHolder(itemView){
        fun bind(item: Driver){
            itemView.findViewById<TextView>(R.id.textviewID).text= item.toString()
        }
    }


}
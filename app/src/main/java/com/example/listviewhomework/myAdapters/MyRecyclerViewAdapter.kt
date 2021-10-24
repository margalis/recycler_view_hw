package com.example.listviewhomework.myAdapters

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.listviewhomework.R
import com.example.listviewhomework.driver.Driver


class MyRecyclerViewAdapter(
    private var objects: List<Driver>,
    val showHideDeleteUpdate: (Boolean) -> Unit
) :
    RecyclerView.Adapter<MyRecyclerViewAdapter.RecyclingViewHolder>() {
    private var mutableObjects = objects.toMutableList()
    var currentSelectedIndex = -1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclingViewHolder =
        RecyclingViewHolder(
            LayoutInflater.from(parent.context.applicationContext)
                .inflate(R.layout.item_list_view, parent, false)
        )


    override fun onBindViewHolder(holder: RecyclingViewHolder, position: Int) {
        holder.bind(objects[position], position)
    }

    override fun getItemCount(): Int = objects.size

    fun removeFromList(): List<Driver> {
        mutableObjects.removeAt(currentSelectedIndex)

        return mutableObjects.also { objects = it }
            .also {
                if (currentSelectedIndex != objects.size) {
                    deselectItem(currentSelectedIndex)
                }
                currentSelectedIndex = -1
            }
    }

    fun addToList(element: Driver): List<Driver> { //ok
        mutableObjects.add(element)
        return mutableObjects.also { objects = it }.also { notifyDataSetChanged() }
    }

    fun setItemTo(name: String, phoneNumber: String, temp: Int): List<Driver> {
        currentSelectedIndex = temp
        val carNumber: String = mutableObjects[currentSelectedIndex].CarNumber
        val oldPhoneNumber = mutableObjects[currentSelectedIndex].phoneNumber
        val oldName = mutableObjects[currentSelectedIndex].name

        mutableObjects[currentSelectedIndex] = Driver(
            if (name == "") oldName else name,
            if (phoneNumber == "") oldPhoneNumber else phoneNumber,
            carNumber
        )

        return mutableObjects.also { objects = it }.also {
            if (currentSelectedIndex != objects.size) {
                deselectItem(currentSelectedIndex)
            }
            currentSelectedIndex = -1
        }
    }

    //why inner though
    inner class RecyclingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: Driver, index: Int) { // elementi type-n  a unenalu
            itemView.findViewById<TextView>(R.id.textviewID).text = item.toString()

            if (item.selected == true) {
                itemView.setBackgroundColor(Color.GREEN)
                // question`  inch vor offsetov khndir ka ,nuyn anun/hamari vra, aranc selectioni guyn a pokhvum
            }
            itemView.setOnLongClickListener { markSelectedItem(index) }
            itemView.setOnClickListener { deselectItem(index) }
        }
    }

    private fun deselectItem(index: Int) {
        if (currentSelectedIndex == index) {
            currentSelectedIndex = -1
            mutableObjects.get(index).selected = false
            showHideDeleteUpdate(false)
            notifyDataSetChanged()

        }
    }

    private fun markSelectedItem(index: Int): Boolean {
        for (item in mutableObjects) {
            item.selected = false
        }
        mutableObjects.get(index).selected = true
        currentSelectedIndex = index
        showHideDeleteUpdate(true)
        notifyDataSetChanged()
        return true
    }


}

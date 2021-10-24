package com.example.listviewhomework.myAdapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.listviewhomework.R
import com.example.listviewhomework.driver.Driver

class MyListViewAdapter(context : Context, private val objects :List<Driver>) :
    ArrayAdapter<Driver>(context, R.layout.item_list_view, R.id.textviewID,objects) {
    override fun getCount(): Int = objects.size

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view=convertView ?: LayoutInflater.from(context).inflate(R.layout.item_list_view,parent,false)
        convertView?.findViewById<TextView>( R.id.textviewID)?.text = objects[position].toString()
        return super.getView(position, convertView, parent)
    }
}
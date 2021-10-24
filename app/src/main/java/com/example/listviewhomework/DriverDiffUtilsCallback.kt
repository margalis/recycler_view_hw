package com.example.listviewhomework

import androidx.recyclerview.widget.DiffUtil
import com.example.listviewhomework.driver.Driver

class DriverDiffUtilsCallback(
    private val oldList: List<Driver>,
    private val newList: List<Driver>
) : DiffUtil.Callback() {
    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].CarNumber == newList[newItemPosition].CarNumber /*&&
                oldList[oldItemPosition].name == newList[newItemPosition].name*/
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }
}
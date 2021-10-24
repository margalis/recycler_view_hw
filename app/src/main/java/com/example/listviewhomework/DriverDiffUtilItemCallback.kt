package com.example.listviewhomework

import androidx.recyclerview.widget.DiffUtil
import com.example.listviewhomework.driver.Driver


class DriverDiffUtilItemCallback: DiffUtil.ItemCallback<Driver>() {
    override fun areItemsTheSame(oldItem: Driver, newItem: Driver): Boolean =
        oldItem.CarNumber==newItem.CarNumber

    override fun areContentsTheSame(oldItem: Driver, newItem: Driver): Boolean=
        oldItem==newItem
}

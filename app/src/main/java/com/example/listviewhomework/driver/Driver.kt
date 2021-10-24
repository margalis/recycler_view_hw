package com.example.listviewhomework.driver

data class Driver(
    val name: String,
    val phoneNumber: String,
    val CarNumber: String,
    var selected: Boolean? = false
) {
    override fun toString(): String {
        return "$name, $phoneNumber, $CarNumber"
    }
}

package com.example.listviewhomework

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.container, CentralFragment())
            .commit()
    }

    override fun onStart() {
        super.onStart()
        println("on Start")
    }

    override fun onResume() {
        super.onResume()
        println("on Resume")
    }

    override fun onPause() {
        super.onPause()
        println("on Pause")
    }

    override fun onStop() {
        super.onStop()
        println("on Stop")
    }

    override fun onDestroy() {
        super.onDestroy()
        println("on Destroy")
    }

    override fun onRestart() {
        super.onRestart()
        println("on Restart")
    }
}
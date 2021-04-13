package com.am.fakestoreapp.ui

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.am.fakestoreapp.R

class Splash : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_layout)
        callHomeActivity()
    }

    fun callHomeActivity() {
        Handler(Looper.getMainLooper()).postDelayed({
            finish()
            startActivity(Intent(this, HomeActivity::class.java))
        }, 1000)
    }
}
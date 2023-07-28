package com.example.myapplication

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class activity_game_pages : AppCompatActivity() {
    val USER_KEY = "user"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_pages)

        // Pulls Username from SharedPreferences for use
        val sharedPreferences = getSharedPreferences("Username", Context.MODE_PRIVATE)
        val Username = sharedPreferences.getString(USER_KEY, null)
    }
}
package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText

class activity_registration : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)

        register()
    }

    fun register() {
        val et_first = findViewById<EditText>(R.id.et_first)
        val et_last = findViewById<EditText>(R.id.et_last)
        val et_email = findViewById<EditText>(R.id.et_email)
        val et_username = findViewById<EditText>(R.id.et_username)
        val et_password = findViewById<EditText>(R.id.et_password)
    }
}
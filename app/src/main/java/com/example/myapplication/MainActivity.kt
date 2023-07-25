package com.example.myapplication

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun Login() {
        val et_username = findViewById<EditText>(R.id.et_username)
        val et_password =  findViewById<EditText>(R.id.et_password)
        val btn_login = findViewById<Button>(R.id.btn_login)
    }
}
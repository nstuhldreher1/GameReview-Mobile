package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        login()
    }

    fun login() {
        val et_username = findViewById<EditText>(R.id.et_username)
        val et_password =  findViewById<EditText>(R.id.et_password)
        val btn_login = findViewById<Button>(R.id.btn_login)

        btn_login.setOnClickListener {
            val intent = Intent(this, activity_registration::class.java)
            startActivity(intent);
        }


    }
}
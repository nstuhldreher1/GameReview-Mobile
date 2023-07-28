package com.example.myapplication

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.statement.HttpResponse
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json

class activity_registration : AppCompatActivity() {
    val USER_KEY = "user"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)

        register()
    }

    fun register() {

        var isValid = true
        val et_first = findViewById<EditText>(R.id.et_first)
        val et_last = findViewById<EditText>(R.id.et_last)
        val et_email = findViewById<EditText>(R.id.et_email)
        val et_username = findViewById<EditText>(R.id.et_username)
        val et_password = findViewById<EditText>(R.id.et_password)
        val btn_signup = findViewById<Button>(R.id.btn_signup)

        btn_signup.setOnClickListener {

            val first = et_first.text.toString()
            val last = et_last.text.toString()
            val email = et_email.text.toString()
            val username = et_username.text.toString()
            val password = et_password.text.toString()

            if (first.isEmpty() || last.isEmpty() || email.isEmpty() || username.isEmpty() || password.isEmpty()) {
                isValid = false;
            }

            val emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\$"
            if (email.matches(emailRegex.toRegex()) === false) {
                isValid = false
            }

            val passwordRexex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#\$%^&+=])(?=\\\\S+\$).{6,10}\$"
            if(password.matches(passwordRexex.toRegex()) === false) {
                isValid = false
            }

            if (isValid) {

               val name = first.plus(" ").plus(last)
                GlobalScope.launch {
                    val result = registerUser(username,name,email,password)
                }
            }
        }


    }

    suspend fun registerUser(username:String, name: String,
                             email: String, password: String)
    {
        val client = HttpClient(CIO) {
            install(ContentNegotiation) {
                json(Json {
                    prettyPrint = true
                    isLenient = true
                })
            }

        }
        val response: HttpResponse = client.post("https://gamereview-debf57bc9a85.herokuapp.com/api/signup") {
            contentType(ContentType.Application.Json)
            setBody(NewUser(username, name, email, password))

        }

        println(response)
        client.close()
    }
}
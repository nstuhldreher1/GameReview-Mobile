package com.example.myapplication
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.post
import io.ktor.client.statement.HttpResponse
import io.ktor.http.ContentType
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.setBody
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json




@Serializable
data class User(val username: String, val password: String)
@Serializable
data class NewUser(val username: String, val name:String, val email: String, val password: String)


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        login()
    }

    fun login() {
        val et_username = findViewById<EditText>(R.id.et_username)
        val et_password = findViewById<EditText>(R.id.et_password)
        val btn_login = findViewById<Button>(R.id.btn_login)

        // Call the async function using coroutines
        GlobalScope.launch {
            connectWebsite()
        }

        btn_login.setOnClickListener {
            val intent = Intent(this, activity_registration::class.java)
            startActivity(intent)
        }
    }

    suspend fun connectWebsite() {
        val client = HttpClient(CIO) {
            install(ContentNegotiation) {
                json(Json {
                    prettyPrint = true
                    isLenient = true
                })
            }

        }
        val response: HttpResponse = client.post("https://gamereview-debf57bc9a85.herokuapp.com/api/login") {
           contentType(ContentType.Application.Json)
            setBody(User("BobEvans", "wow"))

        }

        println(response)
        client.close()
    }

}

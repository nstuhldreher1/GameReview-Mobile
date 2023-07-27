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
data class NewUser(val username: String, val name: String,
    val email: String, val password: String)
@Serializable
data class VerifyUser(val username: String, val code: String)
@Serializable
data class AddReview(val userID: String, val gameID: String,
    val rating: String, val comment: String, val gameName: String)
@Serializable
data class DeleteReview(val userID: String, val gameID: String)
@Serializable
data class EditReview(val userID: String, val gameID: String,
    val rating: String, val comment: String)
@Serializable
data class RequestPassReset(val email: String)
@Serializable
data class VerifyOtp(val email: String, val otp: String)
@Serializable
data class ResetPassword(val newPassword: String, val email: String)
@Serializable
data class SearchGames(val searchGames: String)
@Serializable
data class SearchUsers(val searchUsers: String)
@Serializable
data class LoadGamePage(val gameID: String, val userID: String)












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
            connectWebsite("BobEvans", "wow")
        }

        btn_login.setOnClickListener {
            val intent = Intent(this, activity_registration::class.java)
            startActivity(intent)
        }
    }

    suspend fun connectWebsite(username:String, password:String) {
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
            setBody(User(username, password))

        }

        println(response)
        client.close()
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

    suspend fun verifyUser(username: String, code: String)
    {
        val client = HttpClient(CIO) {
            install(ContentNegotiation) {
                json(Json {
                    prettyPrint = true
                    isLenient = true
                })
            }

        }
        val response: HttpResponse = client.post("https://gamereview-debf57bc9a85.herokuapp.com/api/verify") {
            contentType(ContentType.Application.Json)
            setBody(VerifyUser(username, code))

        }

        println(response)
        client.close()
    }

    suspend fun addReview(userID: String, gameID: String,
                          rating: String, comment: String, gameName:String)
    {
        val client = HttpClient(CIO) {
            install(ContentNegotiation) {
                json(Json {
                    prettyPrint = true
                    isLenient = true
                })
            }

        }
        val response: HttpResponse = client.post("https://gamereview-debf57bc9a85.herokuapp.com/api/addreview") {
            contentType(ContentType.Application.Json)
            setBody(AddReview(userID, gameID, rating, comment, gameName))

        }

        println(response)
        client.close()
    }

    suspend fun deleteReview(userID: String, gameID: String)
    {
        val client = HttpClient(CIO) {
            install(ContentNegotiation) {
                json(Json {
                    prettyPrint = true
                    isLenient = true
                })
            }

        }
        val response: HttpResponse = client.post("https://gamereview-debf57bc9a85.herokuapp.com/api/deletereview") {
            contentType(ContentType.Application.Json)
            setBody(DeleteReview(userID, gameID))

        }

        println(response)
        client.close()
    }
    suspend fun editReview(userID: String, gameID: String,
                           rating: String, comment: String)
    {
        val client = HttpClient(CIO) {
            install(ContentNegotiation) {
                json(Json {
                    prettyPrint = true
                    isLenient = true
                })
            }

        }
        val response: HttpResponse = client.post("https://gamereview-debf57bc9a85.herokuapp.com/api/editReview") {
            contentType(ContentType.Application.Json)
            setBody(EditReview(userID, gameID, rating, comment))

        }

        println(response)
        client.close()
    }

    suspend fun requestPassReset(email: String)
    {
        val client = HttpClient(CIO) {
            install(ContentNegotiation) {
                json(Json {
                    prettyPrint = true
                    isLenient = true
                })
            }

        }
        val response: HttpResponse = client.post("https://gamereview-debf57bc9a85.herokuapp.com/api/requestPassReset") {
            contentType(ContentType.Application.Json)
            setBody(RequestPassReset(email))

        }

        println(response)
        client.close()
    }


    suspend fun verifyOtp(email: String, otp: String)
    {
        val client = HttpClient(CIO) {
            install(ContentNegotiation) {
                json(Json {
                    prettyPrint = true
                    isLenient = true
                })
            }

        }
        val response: HttpResponse = client.post("https://gamereview-debf57bc9a85.herokuapp.com/api/verifyOtp") {
            contentType(ContentType.Application.Json)
            setBody(VerifyOtp(email, otp))

        }

        println(response)
        client.close()
    }

    suspend fun resetPassword(newPassword: String, email: String )
    {
        val client = HttpClient(CIO) {
            install(ContentNegotiation) {
                json(Json {
                    prettyPrint = true
                    isLenient = true
                })
            }

        }
        val response: HttpResponse = client.post("https://gamereview-debf57bc9a85.herokuapp.com/api/resetPassword") {
            contentType(ContentType.Application.Json)
            setBody(ResetPassword(newPassword, email))

        }

        println(response)
        client.close()
    }

    suspend fun searchGames(searchGames: String)
    {
        val client = HttpClient(CIO) {
            install(ContentNegotiation) {
                json(Json {
                    prettyPrint = true
                    isLenient = true
                })
            }

        }
        val response: HttpResponse = client.post("https://gamereview-debf57bc9a85.herokuapp.com/api/searchGames") {
            contentType(ContentType.Application.Json)
            setBody(SearchGames(searchGames))

        }

        println(response)
        client.close()
    }

    suspend fun searchUsers(searchUsers: String)
    {
        val client = HttpClient(CIO) {
            install(ContentNegotiation) {
                json(Json {
                    prettyPrint = true
                    isLenient = true
                })
            }

        }
        val response: HttpResponse = client.post("https://gamereview-debf57bc9a85.herokuapp.com/api/searchUsers") {
            contentType(ContentType.Application.Json)
            setBody(SearchUsers(searchUsers))

        }

        println(response)
        client.close()
    }

    suspend fun loadGamePage(gameID: String, userID: String)
    {
        val client = HttpClient(CIO) {
            install(ContentNegotiation) {
                json(Json {
                    prettyPrint = true
                    isLenient = true
                })
            }

        }
        val response: HttpResponse = client.post("https://gamereview-debf57bc9a85.herokuapp.com/api/verifyOtp") {
            contentType(ContentType.Application.Json)
            setBody(LoadGamePage(gameID, userID))

        }

        println(response)
        client.close()
    }





}

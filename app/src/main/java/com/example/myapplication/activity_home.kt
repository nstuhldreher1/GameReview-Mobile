package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.UserPageData
import io.ktor.client.HttpClient
import io.ktor.client.call.body
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


class activity_home : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ReviewAdapter
    private val reviewList = mutableListOf<ReviewData>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        reviewList.addAll(getReviewDataFromApi())

        adapter = ReviewAdapter(reviewList)
        recyclerView.adapter = adapter
    }


    private fun getReviewDataFromApi(): List<ReviewData> {

        var info: UserPageData
        val job = GlobalScope.launch {
            info = loadUserPage(UserSingleton.userID)

        }
        job.cancel()



        return listOf(

            ReviewData("John Doe", "john_doe_123", "Awesome Game", "This game is amazing!"),
            ReviewData("Jane Smith", "jane_smith_456", "Cool Game", "Great gameplay and graphics!"),
            ReviewData("Bob Johnson", "bob_johnson_789", "Fun Game", "Highly recommended!")
        )
    }


    suspend fun loadUserPage(userID: Int?=null): UserPageData {
        val client = HttpClient(CIO) {
            install(ContentNegotiation) {
                json(Json {
                    prettyPrint = true
                    isLenient = true
                    ignoreUnknownKeys = true
                })
            }

        }
        val response: HttpResponse = client.post("https://gamereview-debf57bc9a85.herokuapp.com/api/loadUserInfo") {
            contentType(ContentType.Application.Json)
            setBody(LoadUserPage(userID))

        }
        println(response.status)

        val responseBody = response.body<UserPageData>()

        client.close()
        return responseBody
    }

}
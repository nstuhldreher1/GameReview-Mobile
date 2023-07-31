package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

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
        return listOf(
            ReviewData("John Doe", "john_doe_123", "Awesome Game", "This game is amazing!"),
            ReviewData("Jane Smith", "jane_smith_456", "Cool Game", "Great gameplay and graphics!"),
            ReviewData("Bob Johnson", "bob_johnson_789", "Fun Game", "Highly recommended!")
        )
    }
}
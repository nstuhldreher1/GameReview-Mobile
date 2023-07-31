package com.example.myapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ReviewAdapter(private val reviewList: List<ReviewData>) :
    RecyclerView.Adapter<ReviewAdapter.ReviewViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReviewViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.review_layout, parent, false)
        return ReviewViewHolder(view)
    }

    override fun onBindViewHolder(holder: ReviewViewHolder, position: Int) {
        val reviewData = reviewList[position]

        // Bind data to the views within the ViewHolder
        holder.mName.text = reviewData.name
        holder.mUsername.text = reviewData.username
        holder.mGame.text = reviewData.game
        holder.mReview.text = reviewData.review
    }

    override fun getItemCount(): Int {
        return reviewList.size
    }

    class ReviewViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        // Declare the views for a single item layout (item_list.xml)
        val mName: TextView = itemView.findViewById(R.id.et_name)
        val mUsername: TextView = itemView.findViewById(R.id.et_username)
        val mGame: TextView = itemView.findViewById(R.id.et_game)
        val mReview: TextView = itemView.findViewById(R.id.et_review)
    }
}

package com.example.themoviebooking.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.themoviebooking.data.vos.MovieVO
import com.example.themoviebooking.delegates.MovieViewHolderDelegate
import com.example.themoviebooking.utils.IMAGE_BASE_URL
import kotlinx.android.synthetic.main.view_holder_movie.view.*

class MovieViewHolder(itemView: View, private val mDelegate: MovieViewHolderDelegate) : RecyclerView.ViewHolder(itemView) {

    private var mMovieVO: MovieVO? = null

    init {
        itemView.setOnClickListener {
            mDelegate.onTapMovie()
        }
    }

    fun bindData(movie: MovieVO) {
        mMovieVO = movie

        Glide.with(itemView.context)
            .load("$IMAGE_BASE_URL${movie.posterPath}")
            .into(itemView.ivMovie)

        itemView.tvMovieTitle.text = movie.title
    }
}
package com.example.themoviebooking.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.themoviebooking.R
import com.example.themoviebooking.adapters.MovieDateAdapter
import com.example.themoviebooking.adapters.MovieTimeAdapter
import com.example.themoviebooking.adapters.TimeChipAdapter
import com.example.themoviebooking.viewholders.MovieTimeChipViewHolder
import kotlinx.android.synthetic.main.activity_movie_date_time.*
import kotlinx.android.synthetic.main.view_holder_movie_time.*

class MovieDateTimeActivity : AppCompatActivity() {

    lateinit var mMovieDateAdapter: MovieDateAdapter
    lateinit var mMovieTimeAdapter: MovieTimeAdapter

    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, MovieDateTimeActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_date_time)

        setUpMovieDateRecyclerView()
        setUpListener()
        setUpMovieTimeRecyclerView()
    }

    private fun setUpMovieTimeRecyclerView() {
        mMovieTimeAdapter = MovieTimeAdapter()
        rvMovieTime.adapter = mMovieTimeAdapter
        rvMovieTime.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
    }

    private fun setUpListener() {
        btnBack.setOnClickListener {
            super.onBackPressed()
        }

        btnNext.setOnClickListener {
            startActivity(MovieSeatActivity.newIntent(this))
        }
    }

    private fun setUpMovieDateRecyclerView() {
        mMovieDateAdapter = MovieDateAdapter()
        rvMovieDate.adapter = mMovieDateAdapter
        rvMovieDate.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
    }
}
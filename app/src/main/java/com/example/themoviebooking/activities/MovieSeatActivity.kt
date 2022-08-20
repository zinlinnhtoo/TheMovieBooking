package com.example.themoviebooking.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import com.example.themoviebooking.R
import com.example.themoviebooking.adapters.MovieSeatAdapter
import com.example.themoviebooking.dummy.DUMMY_SEATS
import kotlinx.android.synthetic.main.activity_movie_seat.*

class MovieSeatActivity : AppCompatActivity() {

    private val mAdapter: MovieSeatAdapter = MovieSeatAdapter()

    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, MovieSeatActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_seat)

        setUpListener()
        setUpMovieRecyclerView()
    }

    private fun setUpMovieRecyclerView() {
        rvMovieSeat.adapter = mAdapter
        rvMovieSeat.layoutManager = GridLayoutManager(applicationContext, 10)
        mAdapter.setNewData(DUMMY_SEATS)
    }

    private fun setUpListener() {
        btnGotoSnack.setOnClickListener {
            startActivity(SnackActivity.newIntent(this))
        }

        btnBack.setOnClickListener {
            super.onBackPressed()
        }
    }
}
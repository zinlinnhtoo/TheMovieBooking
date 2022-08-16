package com.example.themoviebooking.activities

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.themoviebooking.R
import com.example.themoviebooking.adapters.MovieDateAdapter
import com.example.themoviebooking.adapters.MovieTimeAdapter
import com.example.themoviebooking.adapters.TimeChipAdapter
import com.example.themoviebooking.data.vos.DateVO
import com.example.themoviebooking.delegates.MovieDateDelegate
import com.example.themoviebooking.viewholders.MovieTimeChipViewHolder
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_movie_date_time.*
import kotlinx.android.synthetic.main.view_holder_movie_time.*
import java.text.SimpleDateFormat
import java.util.*

class MovieDateTimeActivity : AppCompatActivity(), MovieDateDelegate {

    lateinit var mMovieDateAdapter: MovieDateAdapter
    lateinit var mMovieTimeAdapter: MovieTimeAdapter

    private var mMovieDateList: MutableList<DateVO> = mutableListOf()

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
        addNextTwoWeekDate()
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
        mMovieDateAdapter = MovieDateAdapter(this)
        rvMovieDate.adapter = mMovieDateAdapter
        rvMovieDate.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        mMovieDateAdapter.setNewData(mMovieDateList)
    }

    override fun onTapMovieDate(date: DateVO) {
        Snackbar.make(window.decorView, "${date.id} ${date.year}-${date.month}-${date.day}", Snackbar.LENGTH_LONG).show()
    }

    @SuppressLint("SimpleDateFormat")
    private fun addNextTwoWeekDate() {
        val calendar = Calendar.getInstance()
        val year = SimpleDateFormat("yyyy")
        val month = SimpleDateFormat("MM")
        val day = SimpleDateFormat("dd")
        val weekday = SimpleDateFormat("EEE")

        for (i in 0..13) {
            val date = DateVO(
                id = i,
                year = year.format(calendar.time),
                month = month.format(calendar.time),
                day = day.format(calendar.time),
                weekday = weekday.format(calendar.time)
            )
            calendar.add(Calendar.DATE, 1)
            mMovieDateList.add(i, date)
        }
    }
}
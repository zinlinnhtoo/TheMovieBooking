package com.example.themoviebooking.activities

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.themoviebooking.R
import com.example.themoviebooking.activities.MovieDetailActivity.Companion.EXTRA_MOVIE_ID
import com.example.themoviebooking.adapters.MovieDateAdapter
import com.example.themoviebooking.adapters.MovieTimeAdapter
import com.example.themoviebooking.data.models.MovieBookingModel
import com.example.themoviebooking.data.models.MovieBookingModelImpl
import com.example.themoviebooking.data.vos.DateVO
import com.example.themoviebooking.delegates.MovieDateDelegate
import com.example.themoviebooking.delegates.MovieTimeDelegate
import com.example.themoviebooking.utils.showErrorToast
import kotlinx.android.synthetic.main.activity_movie_date_time.*
import java.text.SimpleDateFormat
import java.util.*

class MovieDateTimeActivity : AppCompatActivity(), MovieDateDelegate, MovieTimeDelegate {

    lateinit var mMovieDateAdapter: MovieDateAdapter
    lateinit var mMovieTimeAdapter: MovieTimeAdapter

    private var mMovieDateList: MutableList<DateVO> = mutableListOf()

    private var mMovieId: Int? = null

    private val mMovieBookingModel: MovieBookingModel = MovieBookingModelImpl

    companion object {
        fun newIntent(context: Context, movieId: Int): Intent {
            val intent = Intent(context, MovieDateTimeActivity::class.java)
            intent.putExtra(EXTRA_MOVIE_ID, movieId)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_date_time)

        setUpMovieDateRecyclerView()
        setUpListener()
        setUpMovieTimeRecyclerView()
        addNextTwoWeekDate()

        mMovieId = intent?.getIntExtra(EXTRA_MOVIE_ID, 0)

        mMovieDateList.firstOrNull()?.let {
            it.isSelected = true
            val date = it.formattedDate()
            requestTimeslotData(date)
        }
    }

    private fun setUpMovieTimeRecyclerView() {
        mMovieTimeAdapter = MovieTimeAdapter(this)
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

    override fun onTapMovieDate(selectedDate: DateVO) {

        for (date in mMovieDateList) {
            date.isSelected = date.id == selectedDate.id
        }
        mMovieDateAdapter.setNewData(mMovieDateList)
        val formattedDate = selectedDate.formattedDate()
        requestTimeslotData(formattedDate)
    }

    override fun onTapTime() {
        Log.i("GG", "OnTap")
        Toast.makeText(this, "Tap Time slot", Toast.LENGTH_SHORT).show()
    }

    private fun requestTimeslotData(date: String) {
        mMovieBookingModel.getCinemaDayTimeslot(
            movieId = mMovieId?.toString().orEmpty(),
            date = date,
            onSuccess = {
                mMovieTimeAdapter.setNewData(it)
            },
            onFailure = {
                showErrorToast(it, this)
            }
        )
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
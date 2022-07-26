package com.example.themoviebooking.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.themoviebooking.R
import com.example.themoviebooking.adapters.CastAdapter
import com.example.themoviebooking.adapters.GenreChipAdapter
import kotlinx.android.synthetic.main.activity_movie_detail.*

class MovieDetailActivity : AppCompatActivity() {

    lateinit var mCastAdapter: CastAdapter
    lateinit var mGenreChipAdapter: GenreChipAdapter

    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, MovieDetailActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)

        setUpCastRecyclerView()
        setUpGenreChipRecyclerView()
        setUpListener()

    }

    private fun setUpGenreChipRecyclerView() {
        mGenreChipAdapter = GenreChipAdapter()
        rvGenreChip.adapter = mGenreChipAdapter
        rvGenreChip.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
    }

    private fun setUpListener() {
        btnBack.setOnClickListener {
            super.onBackPressed()
        }

        btnGetYourTicket.setOnClickListener{
            startActivity(MovieDateTimeActivity.newIntent(this))
        }
    }

    private fun setUpCastRecyclerView() {
        mCastAdapter = CastAdapter()
        rvCast.adapter = mCastAdapter
        rvCast.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
    }
}
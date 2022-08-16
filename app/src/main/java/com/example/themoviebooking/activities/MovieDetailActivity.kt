package com.example.themoviebooking.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.themoviebooking.R
import com.example.themoviebooking.adapters.CastAdapter
import com.example.themoviebooking.adapters.GenreChipAdapter
import com.example.themoviebooking.data.models.TheMovieDBModel
import com.example.themoviebooking.data.models.TheMovieDBModelImpl
import com.example.themoviebooking.data.vos.GenreVO
import com.example.themoviebooking.data.vos.MovieVO
import com.example.themoviebooking.utils.IMAGE_BASE_URL
import com.example.themoviebooking.utils.showErrorToast
import kotlinx.android.synthetic.main.activity_movie_detail.*

class MovieDetailActivity : AppCompatActivity() {

    lateinit var mCastAdapter: CastAdapter
    lateinit var mGenreChipAdapter: GenreChipAdapter

    private var mTheMovieDBModel: TheMovieDBModel = TheMovieDBModelImpl

    companion object {
        private const val EXTRA_MOVIE_ID = "EXTRA_MOVIE_ID"
        fun newIntent(context: Context, movieId: Int): Intent {
            val intent = Intent(context, MovieDetailActivity::class.java)
            intent.putExtra(EXTRA_MOVIE_ID, movieId)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)

        setUpCastRecyclerView()
        setUpGenreChipRecyclerView()
        setUpListener()

        val movieId = intent?.getIntExtra(EXTRA_MOVIE_ID, 0)
        movieId?.let {
            requestData(it.toString())
        }

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

    private fun requestData(movieId: String) {
        mTheMovieDBModel.getMovieDetail(
            movieId = movieId,
            onSuccess = {
                bindData(it)
            },
            onFailure = {
                showErrorToast(it, this)
            }
        )

        mTheMovieDBModel.getCreditsByMovie(
            movieId = movieId,
            onSuccess = {
                mCastAdapter.setNewData(it)
            },
            onFailure = {
                showErrorToast(it, this)
            }
        )
    }

    private fun bindData(movie: MovieVO) {
        Glide.with(this)
            .load("$IMAGE_BASE_URL${movie.posterPath}")
            .into(ivMoviePoster)
        tvMovieTitle.text = movie.title ?: ""
        tvMoviePlayTime.text = movie.runTime?.toString() ?: ""
        rbMovieRatingBar.rating = movie.getRatingBasedOnFiveStars()
        tvImdb.text = movie.voteAverage?.toString() ?: ""
        movie.genres?.let {
            mGenreChipAdapter.setNewData(it)
        }
        tvPlotSummary.text = movie.overView ?: ""
    }

}
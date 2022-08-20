package com.example.themoviebooking.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import com.example.themoviebooking.R
import com.example.themoviebooking.data.models.MovieBookingModelImpl
import com.example.themoviebooking.data.models.TheMovieDBModelImpl
import com.example.themoviebooking.delegates.MovieViewHolderDelegate
import com.example.themoviebooking.utils.showErrorToast
import com.example.themoviebooking.viewpods.MovieListViewPod
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity(), MovieViewHolderDelegate {

    lateinit var mNowShowingMovieListViewPod: MovieListViewPod
    lateinit var mComingSoonMovieListViewPod: MovieListViewPod

    private val mMovieBookingModel = MovieBookingModelImpl
    private val mTheMovieDBModel = TheMovieDBModelImpl

    var actionBarDrawerToggle: ActionBarDrawerToggle? = null

    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, HomeActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        setUpToolbar()
        setUpMovieViewPod()
        setUpDrawer()

        requestData()

        llLogOut.setOnClickListener {
            mMovieBookingModel.getLogout(
                onSuccess = {
                    startActivity(LoginActivity.newLogOutIntent(this, it))
                },
                onFailure = {
                    showErrorToast(it, this)
                }
            )

        }

    }

    private fun setUpDrawer() {
        setSupportActionBar(toolBar)
        actionBarDrawerToggle =
            ActionBarDrawerToggle(this, drawerLayout, R.string.drawer_open, R.string.drawer_close)
        actionBarDrawerToggle?.let {
            drawerLayout.addDrawerListener(it)
            it.syncState()
        }
        toolBar.elevation = 0.0f
        supportActionBar?.elevation = 0.0f
        supportActionBar?.title = ""
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return if (actionBarDrawerToggle?.onOptionsItemSelected(item) == true) {
            true
        } else super.onOptionsItemSelected(item)
    }

    private fun setUpMovieViewPod() {
        mNowShowingMovieListViewPod = vpNowShowingMovie as MovieListViewPod
        mNowShowingMovieListViewPod.setUpMovieListViewPod(this)
        mNowShowingMovieListViewPod.setUpMovieTitle(getString(R.string.lbl_now_showing))

        mComingSoonMovieListViewPod = vpComingSoonMovie as MovieListViewPod
        mComingSoonMovieListViewPod.setUpMovieListViewPod(this)
        mComingSoonMovieListViewPod.setUpMovieTitle(getString(R.string.lbl_coming_soon))

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_home, menu)
        return true
    }

    private fun setUpToolbar() {
        setSupportActionBar(toolBar)
        supportActionBar?.apply {
            setDisplayShowTitleEnabled(false)
            setDisplayHomeAsUpEnabled(true)
            setHomeAsUpIndicator(R.drawable.ic_menu)
        }
    }

    override fun onTapMovie(movieId: Int) {
        startActivity(MovieDetailActivity.newIntent(this, movieId))
    }

    private fun requestData() {

        mMovieBookingModel.getUser(
            onSuccess = {
                tvProfileName.text = it.name
                tvNavigationProfileName.text = it.name
                tvNavigationEmail.text = it.email
            },
            onFailure = {
                showErrorToast(it, this)
            }
        )

        mTheMovieDBModel.getNowShowingMovie(
            onSuccess = {
                mNowShowingMovieListViewPod.setData(it)
            },
            onFailure = {
                showErrorToast(it, this)
            }
        )

        mTheMovieDBModel.getComingSoonMovie(
            onSuccess = {
                mComingSoonMovieListViewPod.setData(it)
            },
            onFailure = {
                showErrorToast(it, this)
            }
        )
    }
}
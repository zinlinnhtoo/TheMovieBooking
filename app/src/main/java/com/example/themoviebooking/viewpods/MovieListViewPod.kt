package com.example.themoviebooking.viewpods

import android.content.Context
import android.util.AttributeSet
import android.widget.RelativeLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.themoviebooking.adapters.MovieListAdapter
import com.example.themoviebooking.delegates.MovieViewHolderDelegate
import kotlinx.android.synthetic.main.view_pod_movie_list.view.*

class MovieListViewPod @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : RelativeLayout(context, attrs) {

    lateinit var mMovieListAdapter : MovieListAdapter
    lateinit var mDelegate: MovieViewHolderDelegate

    override fun onFinishInflate() {
        super.onFinishInflate()
    }

    fun setUpMovieListViewPod(delegate: MovieViewHolderDelegate) {
        setDelegate(delegate)
        setUpRecyclerView()
    }

    fun setUpMovieTitle(titleText: String) {
        tvMovieCategory.text = titleText
    }

    private fun setDelegate(delegate: MovieViewHolderDelegate) {
        this.mDelegate = delegate
    }

    private fun setUpRecyclerView() {
        mMovieListAdapter = MovieListAdapter(mDelegate)
        rvMovieList.adapter = mMovieListAdapter
        rvMovieList.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
    }
}
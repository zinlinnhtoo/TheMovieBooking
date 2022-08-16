package com.example.themoviebooking.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.themoviebooking.R
import com.example.themoviebooking.data.vos.GenreVO
import com.example.themoviebooking.viewholders.GenreChipViewHolder

class GenreChipAdapter: RecyclerView.Adapter<GenreChipViewHolder>() {

    private var mGenreList: List<GenreVO> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenreChipViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_holder_genre_chip, parent, false)
        return GenreChipViewHolder(view)
    }

    override fun onBindViewHolder(holder: GenreChipViewHolder, position: Int) {
        if (mGenreList.isNotEmpty()) {
            holder.bindData(mGenreList[position])
        }
    }

    override fun getItemCount(): Int {
        return mGenreList.count()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setNewData(genreList: List<GenreVO>) {
        mGenreList = genreList
        notifyDataSetChanged()
    }
}
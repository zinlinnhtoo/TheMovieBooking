package com.example.themoviebooking.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.themoviebooking.data.vos.GenreVO
import kotlinx.android.synthetic.main.view_holder_genre_chip.view.*

class GenreChipViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bindData(genre: GenreVO) {
        itemView.tvGenre.text = genre.name
    }
}
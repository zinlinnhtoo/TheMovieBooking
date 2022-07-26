package com.example.themoviebooking.viewholders

import android.annotation.SuppressLint
import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.themoviebooking.R
import com.example.themoviebooking.data.vos.MovieSeatVO
import com.example.themoviebooking.delegates.MovieSeatDelegate
import kotlinx.android.synthetic.main.view_holder_movie_seat.view.*

class MovieSeatViewHolder(
    itemView: View,
    private val mDelegate: MovieSeatDelegate
) : RecyclerView.ViewHolder(itemView) {

    private var totalSeats: Int = 0
    private var takenSeatName: String = ""
    private var removeSeatName: String = ""
    private var ticketPrice: Double = 0.0
    private var row: String = ""

    @SuppressLint("ResourceAsColor")
    fun bindData(data: MovieSeatVO) {
        when {
            data.isMovieSeatAvailable() -> {
                itemView.tvMovieSeatTitle.visibility = View.GONE
                itemView.background = ContextCompat.getDrawable(
                    itemView.context,
                    R.drawable.background_movie_seat_available
                )
                itemView.setOnClickListener {
                    data.isSelected = data.isSelected != true
                    if (data.isSelected == true) {
                        totalSeats = +1
                        data.seatName?.let {
                            takenSeatName = it
                        }
                        data.price?.let {
                            ticketPrice = +it.toDouble()
                        }
                        data.symbol?.let {
                            row = it
                        }
                        itemView.tvMovieSeatTitle.apply {
                            visibility = View.VISIBLE
                            text = data.seatName
                            setTextColor(
                                ContextCompat.getColor(
                                    itemView.context,
                                    R.color.white
                                )
                            )
                        }
                        itemView.flMovieSeat.backgroundTintList = ContextCompat.getColorStateList(
                            itemView.context,
                            R.color.colorPrimary
                        )
                    } else {
                        totalSeats = -1
                        data.seatName?.let {
                            removeSeatName = it
                        }
                        data.price?.let {
                            ticketPrice = -it.toDouble()
                        }
                        data.symbol?.let {
                            row = ""
                        }
                        itemView.tvMovieSeatTitle.visibility = View.GONE
                        itemView.flMovieSeat.backgroundTintList = ContextCompat.getColorStateList(
                            itemView.context,
                            R.color.movie_seat_available_color
                        )
                    }

                    mDelegate.onTapMovieSeat(
                        takenSeatName,
                        removeSeatName,
                        totalSeats,
                        ticketPrice,
                        row
                    )
                }
            }
            data.isMovieSeatTaken() -> {
                itemView.tvMovieSeatTitle.visibility = View.GONE
                itemView.background = ContextCompat.getDrawable(
                    itemView.context,
                    R.drawable.background_movie_seat_taken
                )
            }
            data.isMovieSeatRowTitle() -> {
                itemView.tvMovieSeatTitle.visibility = View.VISIBLE
                itemView.tvMovieSeatTitle.text = data.symbol
                itemView.setBackgroundColor(
                    ContextCompat.getColor(
                        itemView.context,
                        R.color.white
                    )
                )
            }
            else -> {
                itemView.tvMovieSeatTitle.visibility = View.GONE
                itemView.setBackgroundColor(
                    ContextCompat.getColor(
                        itemView.context,
                        R.color.white
                    )
                )
            }
        }
    }
}
package com.example.themoviebooking.persistence

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.themoviebooking.data.vos.*
import com.example.themoviebooking.persistence.daos.*

@Database(
    entities = [
        UserVO::class,
        MovieVO::class,
        SnackVO::class,
        PaymentMethodVO::class,
        DateCinemaAndTimeslotVO::class
    ],
    version = 1,
    exportSchema = false
)
abstract class MovieBookingDatabase : RoomDatabase() {

    companion object {
        const val DB_NAME = "THE_MOVIE_BOOKING_DB"
        var dbInstance: MovieBookingDatabase? = null
        fun getDBInstance(context: Context): MovieBookingDatabase? {
            when (dbInstance) {
                null -> {
                    dbInstance = Room.databaseBuilder(
                        context,
                        MovieBookingDatabase::class.java,
                        DB_NAME
                    )
                        .allowMainThreadQueries()
                        .fallbackToDestructiveMigration()
                        .build()
                }
            }
            return dbInstance
        }
    }

    abstract fun userDao(): UserDao
    abstract fun movieDao(): MovieDao
    abstract fun snackDao(): SnackDao
    abstract fun paymentMethodDao(): PaymentMethodDao
    abstract fun dateCinemaAndTimeslot(): DateCinemaAndTimeslotDao
}
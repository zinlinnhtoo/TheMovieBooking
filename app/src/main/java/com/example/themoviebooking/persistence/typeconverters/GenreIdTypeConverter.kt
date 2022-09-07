package com.example.themoviebooking.persistence.typeconverters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class GenreIdTypeConverter {
    @TypeConverter
    fun toString(genreList: List<Int>?): String {
        return Gson().toJson(genreList)
    }

    @TypeConverter
    fun toGenreIds(genreIdsListJsonString: String): List<Int>? {
        val genreIdsListType = object : TypeToken<List<Int>?>() {}.type
        return Gson().fromJson(genreIdsListJsonString, genreIdsListType)
    }
}
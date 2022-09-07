package com.example.themoviebooking.persistence.typeconverters

import androidx.room.TypeConverter
import com.example.themoviebooking.data.vos.SpokenLanguageVO
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class SpokenLanguageTypeConverter {
    @TypeConverter
    fun toString(spokenLanguageList: List<SpokenLanguageVO>?): String {
        return Gson().toJson(spokenLanguageList)
    }

    @TypeConverter
    fun toSpokenLanguageList(spokenLanguageListJsonString: String): List<SpokenLanguageVO>? {
        val spokenLanguageListType = object : TypeToken<List<SpokenLanguageVO>?>() {}.type
        return Gson().fromJson(spokenLanguageListJsonString, spokenLanguageListType)
    }
}

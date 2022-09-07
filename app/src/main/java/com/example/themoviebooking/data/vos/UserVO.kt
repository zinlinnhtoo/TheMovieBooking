package com.example.themoviebooking.data.vos

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.themoviebooking.persistence.typeconverters.CardsTypeConverter
import com.google.gson.annotations.SerializedName

@Entity(tableName = "users")
@TypeConverters(
    CardsTypeConverter::class
)
data class UserVO(
    @SerializedName("id")
    @PrimaryKey
    val id: Int? = 0,

    @SerializedName("name")
    @ColumnInfo(name = "name")
    val name: String?,

    @SerializedName("email")
    @ColumnInfo(name = "email")
    val email: String?,

    @SerializedName("phone")
    @ColumnInfo(name = "phone")
    val phone: String?,

    @SerializedName("total_expense")
    @ColumnInfo(name = "total_expense")
    val totalExpense: Int? = 0,

    @SerializedName("profile_image")
    @ColumnInfo(name = "profile_image")
    val profileImage: String?,

    @SerializedName("cards")
    @ColumnInfo(name = "cards")
    val cards: List<CardVO>?

)
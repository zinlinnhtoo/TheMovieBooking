package com.example.themoviebooking.data.vos

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "snacks")
data class SnackVO(
    @SerializedName("id")
    @PrimaryKey
    val id: Int? = 0,

    @SerializedName("name")
    @ColumnInfo(name = "name")
    val name: String?,

    @SerializedName("description")
    @ColumnInfo(name = "description")
    val description: String?,

    @SerializedName("price")
    @ColumnInfo(name = "price")
    val price: Double?,

    @SerializedName("image")
    @ColumnInfo(name = "image")
    val image: String?,

    var quantity: Int = 0,
    var totalPrice: Double = 0.0,
    var isSelectedMinusButton: Boolean? = true
)

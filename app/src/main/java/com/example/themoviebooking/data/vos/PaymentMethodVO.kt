package com.example.themoviebooking.data.vos

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "payment_methods")
data class PaymentMethodVO(
    @SerializedName("id")
    @PrimaryKey
    val id: Int? = 0,

    @SerializedName("name")
    @ColumnInfo(name = "name")
    val name: String?,

    @SerializedName("description")
    @ColumnInfo(name = "description")
    val description: String?,

    var isSelected: Boolean? = false
)

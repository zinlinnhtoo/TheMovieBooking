package com.example.themoviebooking.data.vos

data class DateVO(
    var id: Int = 0,
    var year: String,
    var month: String,
    var day: String,
    var weekday: String,
    var isSelected: Boolean? = false
) {
    fun formattedDate(): String {
        return "${year}-${month}-${day}"
    }
}

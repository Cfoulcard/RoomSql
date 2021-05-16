package com.example.roomsql.util

import java.text.DateFormat.getDateInstance
import java.util.*

private const val TAG = "Timestamp"

/**
 * Returns properties concerning the date.This is a singleton used to initialize whenever
 * needed in other class without causing threading issues. Initialize simply by calling the object
 * name.
 */
object Timestamp {

     // Find today's date
     fun getCurrentTimeStamp(): String? {

         val dateFormat =
             getDateInstance()

         return dateFormat.format(Date()) // Finds today's date
     }

    fun getMonthFromNumber(monthNumber: String?): String {
       return when (monthNumber) {
            "01" -> { "Jan" }
            "02" -> { "Feb" }
            "03" -> { "Mar" }
            "04" -> { "Apr" }
            "05" -> { "May" }
            "06" -> { "Jun" }
            "07" -> { "Jul" }
            "08" -> { "Aug" }
            "09" -> { "Sep" }
            "10" -> { "Oct" }
            "11" -> { "Nov" }
            "12" -> { "Dec" }

           else -> "Error"
       }
    }
}
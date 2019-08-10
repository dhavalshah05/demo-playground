package com.example.demoplayground.utils

import java.text.SimpleDateFormat
import java.util.*

@Suppress("unused")
object DateUtils {

    enum class DateFormat(val value: String) {
        YYYY_MM_DD("yyyy-MM-dd"),
        YYYY_MM_DD_HH_MM_SS("yyyy-MM-dd HH:mm:ss"),
        YYYY_MM_DD_T_HH_MM_SS_SSS_Z("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"),
    }

    enum class DateTimeZone(val timeZone: TimeZone) {
        DEFAULT(TimeZone.getDefault()),
        UTC(TimeZone.getTimeZone("UTC"))
    }

    /**
     * Format the provided date string according to DateFormat and DateTimeZone.
     */
    fun format(
        inputDate: String,
        inFormat: DateFormat,
        outFormat: DateFormat,
        inTimeZone: DateTimeZone? = null,
        outTimeZone: DateTimeZone? = null
    ): String {
        val inputDateFormat = SimpleDateFormat(inFormat.value, Locale.getDefault())
        val outputDateFormat = SimpleDateFormat(outFormat.value, Locale.getDefault())

        if (inTimeZone != null) {
            inputDateFormat.timeZone = inTimeZone.timeZone
        }

        if (outTimeZone != null) {
            outputDateFormat.timeZone = outTimeZone.timeZone
        }

        val date = inputDateFormat.parse(inputDate)
        return outputDateFormat.format(date!!)
    }

    /**
     * Format the provided date according to DateFormat and DateTimeZone.
     */
    fun format(
        inputDate: Date,
        outFormat: DateFormat,
        outTimeZone: DateTimeZone? = null
    ): String {
        val outputDateFormat = SimpleDateFormat(outFormat.value, Locale.getDefault())

        if (outTimeZone != null) {
            outputDateFormat.timeZone = outTimeZone.timeZone
        }

        return outputDateFormat.format(inputDate)
    }

    /**
     * Get the current date according to DateFormat and DateTimeZone.
     */
    fun getCurrentDate(
        outFormat: DateFormat,
        outTimeZone: DateTimeZone? = null
    ): String {
        val outputDateFormat = SimpleDateFormat(outFormat.value, Locale.getDefault())
        if (outTimeZone != null) {
            outputDateFormat.timeZone = outTimeZone.timeZone
        }
        val date = Calendar.getInstance()
        return outputDateFormat.format(date.time)
    }

    /**
     * Parse the date string according to DateFormat and DateTimeZone.
     */
    fun parseDate(
        inputDate: String,
        inFormat: DateFormat,
        inTimeZone: DateTimeZone? = null
    ): Date {
        val inputDateFormat = SimpleDateFormat(inFormat.value, Locale.getDefault())
        if (inTimeZone != null) {
            inputDateFormat.timeZone = inTimeZone.timeZone
        }
        return inputDateFormat.parse(inputDate)!!
    }

    /**
     * Get Date for given year, month and day.
     */
    fun getDate(year: Int, month: Int, day: Int): Date {
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.YEAR, year)
        calendar.set(Calendar.MONTH, month)
        calendar.set(Calendar.DAY_OF_MONTH, day)

        clearCalendarTime(calendar)
        return calendar.time
    }

    /**
     * Clear time for given Calendar instance.
     */
    private fun clearCalendarTime(calendar: Calendar) {
        calendar.set(Calendar.HOUR_OF_DAY, 0)
        calendar.set(Calendar.MINUTE, 0)
        calendar.set(Calendar.SECOND, 0)
        calendar.set(Calendar.MILLISECOND, 0)
    }
}
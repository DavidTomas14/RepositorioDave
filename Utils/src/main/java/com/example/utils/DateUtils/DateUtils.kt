package com.example.utils.DateUtils

import android.os.Build.VERSION_CODES
import androidx.annotation.RequiresApi
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException

@RequiresApi(VERSION_CODES.O)
object DateUtils {


    /**
     * Return date as format dd/MM/yyyy
     */

    fun formatDate(date: String): String {
        val df = DateTimeFormatter.ofPattern("dd/MM/yyyy")
        lateinit var localDateTime: LocalDateTime
        return try {
            localDateTime = LocalDateTime.parse(date)
            localDateTime.format(df)
        } catch (e: DateTimeParseException) {
            date
        }
    }

    // Return different format ej. 21 marzo 2020 12:01:02
    fun formatDateAndTime(date: String): String {
        lateinit var localDateTime: LocalDateTime
        return try {
            localDateTime = LocalDateTime.parse(date)
            localDateTime.format(DateTimeFormatter.ofPattern("dd MMMM yyyy, HH:mm:ss"))
        } catch (e: DateTimeParseException) {
            date
        }
    }

    fun getLocalDateTime(dateStr: String): LocalDateTime {
        return LocalDateTime.parse(dateStr)
    }

    fun formatTime(time: LocalDateTime): String {
        return time.format(DateTimeFormatter.ofPattern("HH:mm"))
    }

    fun getTimeFromhhmm(time: String): LocalDateTime {
        val localTime = LocalTime.parse(time, DateTimeFormatter.ofPattern("HH:mm"))
        val localDateNow = LocalDate.now()
        return LocalDateTime.of(localDateNow, localTime)
    }

    /**
     * fecha = dd/MM/yyyy
     * hora = hh:mm
     * returns LocalDateTime as String
     */
    fun getStringFromFechaHora(fecha: String, hora: String = "00:00"): String {
        val dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
        val timeFormatter = DateTimeFormatter.ofPattern("HH:mm")
        return LocalDateTime.of(LocalDate.parse(fecha, dateFormatter), LocalTime.parse(hora, timeFormatter)).toString()
    }
}
package com.example.myapplication.presentation.utils.extension_functions

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

object DateTimeFormats{
    const val UTC_FORMAT = "yyyy-MM-dd'T'HH:mm:ss'Z'"
    const val APP_DATE_FORMAT = "yyyy-MM-dd"
    const val APP_DATE_TIME_FORMAT = "yyyy-MM-dd hh:mm a"
}

@RequiresApi(Build.VERSION_CODES.O)
fun String.toLocalDate(
    format:String = DateTimeFormats.UTC_FORMAT,
) : LocalDate?{
    return try{
        val formatter = DateTimeFormatter.ofPattern(format)
        LocalDateTime.parse(this, formatter).toLocalDate()
    }catch (e:Exception){
        null
    }
}

@RequiresApi(Build.VERSION_CODES.O)
fun LocalDate.strToShow(format:String = DateTimeFormats.APP_DATE_FORMAT):String{
    val formatter = DateTimeFormatter.ofPattern(format)
    return this.format(formatter)
}

@RequiresApi(Build.VERSION_CODES.O)
fun String.toLocalDateTime(
    format:String = DateTimeFormats.UTC_FORMAT,
) : LocalDateTime?{
    return try{
        val formatter = DateTimeFormatter.ofPattern(format)
        LocalDateTime.parse(this, formatter)
    }catch (e:Exception){
        null
    }
}

@RequiresApi(Build.VERSION_CODES.O)
fun LocalDateTime.strToShow(format:String = DateTimeFormats.APP_DATE_TIME_FORMAT):String{
    val formatter = DateTimeFormatter.ofPattern(format)
    return this.format(formatter)
}

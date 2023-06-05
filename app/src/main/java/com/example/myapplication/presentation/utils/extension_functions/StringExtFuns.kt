package com.example.myapplication.presentation.utils.extension_functions

fun String?.capitalize():String{
    return this?.split(' ')
        ?.joinToString(" ") {
            it.replaceFirstChar(Char::uppercaseChar)
        } ?: ""
}

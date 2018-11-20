package com.test.kotlinapp.utils

import android.util.Log

object Logger {
    const val TAG: String = "App"

    fun d(msg: String) {
        Log.d(TAG, "**** -> $msg")
    }

    fun logThreadDetails(from:String) {
        val threadId = Thread.currentThread().id
        val name = Thread.currentThread().name
        d("$from  --> Thread # $threadId with name = $name")
    }
}
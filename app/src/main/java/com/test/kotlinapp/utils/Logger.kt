package com.test.kotlinapp.utils

import android.util.Log

object Logger {
    const val TAG: String = "App"

    fun d(msg: String) {
        Log.d(TAG, "**** -> $msg")
    }
}
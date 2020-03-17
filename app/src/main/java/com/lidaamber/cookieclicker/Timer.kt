package com.lidaamber.cookieclicker

import android.os.Handler
import android.util.Log

/**
 * @author lidaamber
 */
class Timer {

    private lateinit var runnable: Runnable
    private val handler = Handler()

    var value: Int = 0

    fun start() {
        runnable = Runnable {
            value += 1
            Log.i("Timer", "Timer: $value")
            handler.postDelayed(runnable, 1000L)
        }

        handler.postDelayed(runnable, 1000L)
    }

    fun stop() {
        handler.removeCallbacks(runnable)
    }
}

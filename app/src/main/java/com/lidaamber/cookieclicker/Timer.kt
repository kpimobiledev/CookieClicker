package com.lidaamber.cookieclicker

import android.os.Handler
import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent

/**
 * @author lidaamber
 */
class Timer(lifecycle: Lifecycle) : LifecycleObserver {

    private lateinit var runnable: Runnable
    private val handler = Handler()

    var value: Int = 0

    init {
        lifecycle.addObserver(this)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun start() {
        runnable = Runnable {
            value += 1
            Log.i("Timer", "Timer: $value")
            handler.postDelayed(runnable, 1000L)
        }

        handler.postDelayed(runnable, 1000L)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun stop() {
        handler.removeCallbacks(runnable)
    }
}

package com.lidaamber.cookieclicker

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.core.app.ShareCompat
import androidx.databinding.DataBindingUtil
import com.lidaamber.cookieclicker.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var timer: Timer
    private lateinit var binding: ActivityMainBinding
    private var gameInfo = GameInfo()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i(TAG, "onCreate called")
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.gameInfo = gameInfo
        binding.cookieImage.setOnClickListener {
            increaseScore()
        }

        timer = Timer(lifecycle)
        savedInstanceState?.let {
            timer.value = it.getInt(TIMER_VALUE)
            gameInfo.score = it.getInt(SCORE)
            gameInfo.rank = it.getInt(RANK)
            binding.invalidateAll()
        }
    }

    private fun increaseScore() {
        gameInfo.score += 1
        binding.invalidateAll()
    }

    override fun onStart() {
        super.onStart()
        Log.i(TAG, "onStart called")
    }

    override fun onResume() {
        super.onResume()
        Log.i(TAG, "onResume called")
    }

    override fun onPause() {
        super.onPause()
        Log.i(TAG, "onPause called")
    }

    override fun onStop() {
        super.onStop()
        Log.i(TAG, "onStop called")
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        outState?.putInt(TIMER_VALUE, timer.value)
        outState?.putInt(SCORE, gameInfo.score)
        outState?.putInt(RANK, gameInfo.rank)
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i(TAG, "onDestroy called")
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.share_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item!!.itemId == R.id.share) {
            startActivity(Intent.createChooser(getShareIntent(), getString(R.string.share)))
        }
        return super.onOptionsItemSelected(item)
    }

    private fun getShareIntent(): Intent {
        return ShareCompat.IntentBuilder.from(this)
                .setText(getString(R.string.stats, getString(gameInfo.rank), gameInfo.score))
                .setType("text/plain")
                .intent
    }

    companion object {
        const val TAG = "MainActivity"

        const val TIMER_VALUE = "timerValue"
        const val SCORE = "score"
        const val RANK = "rank"
    }
}

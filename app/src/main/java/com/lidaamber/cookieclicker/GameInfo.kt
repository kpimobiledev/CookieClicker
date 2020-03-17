package com.lidaamber.cookieclicker

/**
 * @author lidaamber
 */
class GameInfo {
    var rank = R.string.start
    var score = 0
        set(value) {
            rank = when (value) {
                20 -> R.string.going_steady
                50 -> R.string.medium
                100 -> R.string.hard
                500 -> R.string.insane
                1000 -> R.string.unbelievable
                10000 -> R.string.cookie_god
                else -> rank
            }
            field = value
        }
}
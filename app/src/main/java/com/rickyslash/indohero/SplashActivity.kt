package com.rickyslash.indohero

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        supportActionBar?.hide()

    val splashTimer = object : CountDownTimer(1500, 1000) {
        override fun onTick(millisUntilFinished: Long) { }

        override fun onFinish() {
            val moveMainActivityIntent = Intent(this@SplashActivity, MainActivity::class.java)
            startActivity(moveMainActivityIntent)
            finish()
        }
    }

    splashTimer.start()

    }
}
package com.choota.opencurrency.presentation.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.choota.opencurrency.R
import com.choota.opencurrency.presentation.converter.ConverterActivity

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        startActivity(Intent(this, ConverterActivity::class.java))
        finish()
    }
}
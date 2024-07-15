package com.example.apiapi.uis

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.apiapi.R
import com.example.apiapi.databinding.ActivityWelcomeScreenBinding

class WelcomeScreen : AppCompatActivity() {
    private lateinit var binding: ActivityWelcomeScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding= DataBindingUtil.setContentView(this@WelcomeScreen,
            R.layout.activity_welcome_screen
        )
        with(binding){
            val welcomeText="welcome"
            val spannableString= SpannableString(welcomeText)
            spannableString.setSpan(ForegroundColorSpan(Color.parseColor("#800000")),0,5,0)
            spannableString.setSpan(ForegroundColorSpan(Color.parseColor("#800000")),5,welcomeText.length,0)

            welcometext.text=spannableString
            Handler(Looper.getMainLooper()).postDelayed({
                startActivity(Intent(this@WelcomeScreen, LoginActivity::class.java))
                finish()
            },3000)
            }

        }

    }


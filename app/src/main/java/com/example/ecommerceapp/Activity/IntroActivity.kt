package com.example.ecommerceapp.Activity

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.ecommerceapp.R
import com.example.ecommerceapp.databinding.ActivityIntroBinding
import com.example.ecommerceapp.databinding.ActivityMainBinding
import com.google.android.gms.common.SignInButton

class IntroActivity : baseActivity() {
    private lateinit var binding: ActivityIntroBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityIntroBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.SignUpBtn.setOnClickListener {
            startActivity(Intent(this@IntroActivity, SignUpActivity::class.java))
        }

        binding.SignTxt.setOnClickListener {
            startActivity(Intent(this@IntroActivity, SignInActivity::class.java))
        }

    }
}
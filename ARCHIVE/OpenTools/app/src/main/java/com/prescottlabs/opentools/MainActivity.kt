package com.prescottlabs.opentools

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.prescottlabs.opentools.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tipperButton.setOnClickListener { openTipperActivity() }
    }

    private fun openTipperActivity() {
        val intent = Intent(this, TipperActivity::class.java)
        startActivity(intent)
    }
}
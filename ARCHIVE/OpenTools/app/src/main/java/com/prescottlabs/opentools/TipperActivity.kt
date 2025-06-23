package com.prescottlabs.opentools

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.widget.addTextChangedListener
import com.prescottlabs.opentools.R
import com.prescottlabs.opentools.databinding.ActivityTipperBinding
import java.text.NumberFormat

class TipperActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTipperBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTipperBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.splitText.text = getString(R.string.split, binding.splitResult.value.toString())
        binding.serviceQuestion.text = getString(R.string.how_was_the_service, binding.tipOptions.value.toString())

        binding.costOfServiceEditText.addTextChangedListener { calculateTip() }
        binding.roundUpSwitch.setOnClickListener { calculateTip() }
        binding.splitResult.addOnChangeListener { _, _, _ -> calculateTip() }
        binding.tipOptions.addOnChangeListener { _, _, _ -> calculateTip() }

        binding.cameraButton.setOnClickListener { openCameraActivity() }
    }

    private fun calculateTip() {
        val costString = binding.costOfServiceEditText.text.toString()

        if (costString.isNotEmpty()) {
            val tipPercent = binding.tipOptions.value.toDouble() / 100

            val split = binding.splitResult.value
            val cost = costString.toDouble() / split
            var tip = cost * tipPercent

            if (binding.roundUpSwitch.isChecked) {
                tip = kotlin.math.ceil(tip)
            }

            display(tip, cost + tip)
        } else {
            display(0.00, 0.00)
        }
    }

    private fun display(tip: Double, cost: Double) {
        val formattedTip = NumberFormat.getCurrencyInstance().format(tip)
        val formattedTotal = NumberFormat.getCurrencyInstance().format(cost)

        binding.serviceQuestion.text = getString(R.string.how_was_the_service, binding.tipOptions.value.toString())
        binding.splitText.text = getString(R.string.split, binding.splitResult.value.toString())
        binding.tipResult.text = getString(R.string.tip_amount, formattedTip)
        binding.totalResult.text = getString(R.string.total_amount, formattedTotal)
    }

    private fun openCameraActivity() {
        val intent = Intent(this, CameraActivity::class.java)
        startActivity(intent)
    }
}
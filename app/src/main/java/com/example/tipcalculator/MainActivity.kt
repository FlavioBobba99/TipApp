package com.example.tipcalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.tipcalculator.databinding.ActivityMainBinding
import java.text.NumberFormat

class MainActivity : AppCompatActivity() {

    /**Attributes declaration
     * i declare a acitibitymainbinding var
     * with a lateinit type, i promise that the variable will be
     * initialized later*/
    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        displayTip(0.0)
        binding.calculateButton.setOnClickListener { calculateTip() }

    }

    private fun calculateTip() {
        val stringFromTextField = binding.costOfServiceEditText.text.toString()
        val cost = stringFromTextField.toDoubleOrNull()

        val tipPercentage = when (binding.rgQualityOfService.checkedRadioButtonId) {
            R.id.option_twenty_percent -> 0.20
            R.id.option_eighteen_percent -> 0.18
            R.id.option_fifteen_percent -> 0.15
            else -> 0.20
        }
        /**null check*/
        if(cost == null) {
            displayTip(0.0)
            Toast.makeText(applicationContext, "Please Insert a value", Toast.LENGTH_SHORT).show()
            return
        }

        var tip = tipPercentage * cost
        val roundUp = binding.roundTipOption.isChecked

        if(roundUp) {
            tip = kotlin.math.ceil(tip)
        }
        displayTip(tip)
    }

    private fun displayTip (tip : Double) {
        val formattedTip = NumberFormat.getCurrencyInstance().format(tip)
        binding.displayTip.text = getString(R.string.tip_amount, formattedTip)
    }
}
package com.example.usd_to_bdt_converstion_with_tax

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.view.isEmpty
import androidx.databinding.DataBindingUtil
import com.example.usd_to_bdt_converstion_with_tax.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    lateinit var convertRate : Conversion
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.btnConvert.setOnClickListener {

            var conversion= Conversion(binding.usdAmount.text.toString().toDouble())
            var taxAmount = conversion.usd +(conversion.usd * (conversion.tax/100))


            Toast.makeText(this, "${binding.tax.text}", Toast.LENGTH_LONG).show()
        }
    }
}
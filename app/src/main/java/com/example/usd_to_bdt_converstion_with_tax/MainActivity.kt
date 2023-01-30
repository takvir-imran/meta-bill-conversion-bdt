package com.example.usd_to_bdt_converstion_with_tax

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.core.view.isEmpty
import androidx.databinding.DataBindingUtil
import com.example.usd_to_bdt_converstion_with_tax.databinding.ActivityMainBinding
import java.text.DecimalFormat

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    lateinit var conversion : Conversion
    val df = DecimalFormat("#.##")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.btnConvert.setOnClickListener {


            if(binding.tax.text!!.isEmpty() && binding.conversionRate.text!!.isEmpty()){
                conversion= Conversion(binding.usdAmount.text.toString().toDouble())
            }
            else if(binding.conversionRate.text!!.isEmpty()){
                conversion= Conversion(binding.usdAmount.text.toString().toDouble(), binding.tax.text.toString().toDouble())
            }
            else if(binding.tax.text!!.isEmpty()){
                conversion= Conversion(binding.usdAmount.text.toString().toDouble(), conversionRate= binding.conversionRate.text.toString().toDouble())
            }
            else{
                conversion= Conversion(binding.usdAmount.text.toString().toDouble(), conversionRate= binding.conversionRate.text.toString().toDouble(), tax = binding.tax.text.toString().toDouble() )
            }
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(binding.btnConvert.windowToken, 0)

            var taxAmount = (conversion.usd * (conversion.tax/100))
            var convertedUSD =conversion.usd *  conversion.conversionRate
            var convertedTAX = taxAmount * conversion.conversionRate

            binding.linearLayoutBreakdown.visibility= View.VISIBLE
            binding.usdConversion.text = "Actual Bill: ${conversion.usd} USD x ${conversion.conversionRate} Taka = $convertedUSD Taka"
            binding.taxConversion.text = "Tax: $taxAmount USD x ${conversion.conversionRate} Taka = ${df.format(convertedTAX)} Taka"
            binding.totalAmount.text = "Total: ${conversion.usd+taxAmount} USD x ${conversion.conversionRate} Taka = ${df.format(convertedUSD+convertedTAX)} Taka"
        }
    }
}
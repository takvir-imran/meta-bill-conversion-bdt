package com.example.usd_to_bdt_converstion_with_tax

data class Conversion(var usd: Double, var tax: Double= 32.0, var conversionRate: Double = 106.0)

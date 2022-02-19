package net.mustafabaser.dkscurrencyconversion

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

var baseCurrency = "EUR"
//var targetCurrency = "USD"
var conversionRate = 0f

class CustomCurrency : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_custom_currency)
    }
}
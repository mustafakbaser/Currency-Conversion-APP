package net.mustafabaser.dkscurrencyconversion

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.*
import kotlinx.android.synthetic.main.activity_custom_currency.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONObject
import java.net.URL

var baseCurrency = "EUR"
var targetCurrency = "USD"
var conversionRate = 0f
var amount = 1

var API =
    "https://api.frankfurter.app/latest?amount=1&from=EUR&to=$baseCurrency"

class CustomCurrency : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_custom_currency)
        spinnerSetup()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if (id == R.id.descriptionButton) {
            val intent = Intent(this, DescriptionActivity::class.java)
            startActivity(intent)
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    private fun spinnerSetup() {

        val spinner: Spinner = findViewById(R.id.targetCurrency)

        ArrayAdapter.createFromResource(
            this,
            R.array.currencies,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adapter
        }

        ArrayAdapter.createFromResource(
            this,
            R.array.currencies2,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        }

        spinner.onItemSelectedListener = (object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                baseCurrency = parent?.getItemAtPosition(position).toString()
                getApiResult()
            }
        })
    }

    private fun getApiResult() {
        //API Document: https://www.frankfurter.app/docs/
        //TO-DO
        var fromCurrency = "EUR"
        if(baseCurrency != fromCurrency) {
            var API =
                "https://api.frankfurter.app/latest?amount=1&from=$fromCurrency&to=$baseCurrency"
        }
        //baseCurrency ile hedef aynı olduğunda repo patlıyor.
        when(baseCurrency){
            "EUR" -> {
                var fromCurrency = "EUR"
                var API =
                    "https://api.frankfurter.app/latest?amount=1&from=$fromCurrency&to=$baseCurrency"
                GlobalScope.launch(Dispatchers.IO) {
                    try {
                        val apiResult = URL(API).readText()
                        val jsonObject = JSONObject(apiResult)
                        conversionRate =
                            jsonObject.getJSONObject("rates").getString(baseCurrency)
                                .toFloat()

                        val usdApiResult = URL(API).readText()
                        val usdJsonObject = JSONObject(usdApiResult)
                        var usdConversionRate =
                            usdJsonObject.getJSONObject("rates").getString("USD")
                                .toFloat()

                        Log.d("Main", "$conversionRate")
                        Log.d("Main", apiResult)

                        withContext(Dispatchers.Main) {
                            val text =
                                ((amount.toFloat()) * conversionRate).toString()
                            euroToCurrency.setText(text)

                            val usdText =
                                ((amount.toFloat()) * usdConversionRate).toString()
                            usdToCurrency.setText(usdText)

                            val tryText =
                                ((amount.toFloat()) * conversionRate).toString()
                            tryToCurrency.setText(tryText)

                            val gbpText =
                                ((amount.toFloat()) * conversionRate).toString()
                            tryToCurrency.setText(tryText)
                        }

                    } catch (e: Exception) {
                        Log.e("Main", "$e")
                    }
                }
            }

            "USD" -> {
                var fromCurrency = "USD"
                var API =
                    "https://api.frankfurter.app/latest?amount=1&from=$fromCurrency&to=$baseCurrency"
            }
            "TRY" -> {
                var fromCurrency = "TRY"
                var API =
                    "https://api.frankfurter.app/latest?amount=1&from=$fromCurrency&to=$baseCurrency"
            }
            "GBP" -> {
                var fromCurrency = "GBP"
                var API =
                    "https://api.frankfurter.app/latest?amount=1&from=$fromCurrency&to=$baseCurrency"
            }
            "JPY" -> {
                var fromCurrency = "JPY"
                var API =
                    "https://api.frankfurter.app/latest?amount=1&from=$fromCurrency&to=$baseCurrency"
            }
            "CHF" -> {
                var fromCurrency = "CHF"
                var API =
                    "https://api.frankfurter.app/latest?amount=1&from=$fromCurrency&to=$baseCurrency"
            }
            "CNY" -> {
                var fromCurrency = "CNY"
                var API =
                    "https://api.frankfurter.app/latest?amount=1&from=$fromCurrency&to=$baseCurrency"
            }
            "DKK" -> {
                var fromCurrency = "DKK"
                var API =
                    "https://api.frankfurter.app/latest?amount=1&from=$fromCurrency&to=$baseCurrency"
            }
            "AUD" -> {
                var fromCurrency = "AUD"
                var API =
                    "https://api.frankfurter.app/latest?amount=1&from=$fromCurrency&to=$baseCurrency"
            }
            "CAD" -> {
                var fromCurrency = "CAD"
                var API =
                    "https://api.frankfurter.app/latest?amount=1&from=$fromCurrency&to=$baseCurrency"
            }
        }

        GlobalScope.launch(Dispatchers.IO) {
            try {
                val apiResult = URL(API).readText()
                val jsonObject = JSONObject(apiResult)
                conversionRate =
                    jsonObject.getJSONObject("rates").getString(baseCurrency)
                        .toFloat()

                Log.d("Main", "$conversionRate")
                Log.d("Main", apiResult)

                withContext(Dispatchers.Main) {
                    val text =
                        ((amount.toFloat()) * conversionRate).toString()
                    euroToCurrency.setText(text)

                    val usdText =
                        ((amount.toFloat()) * conversionRate).toString()
                    usdToCurrency.setText(usdText)

                    val tryText =
                        ((amount.toFloat()) * conversionRate).toString()
                    tryToCurrency.setText(tryText)

                    val gbpText =
                        ((amount.toFloat()) * conversionRate).toString()
                    tryToCurrency.setText(tryText)
                }

            } catch (e: Exception) {
                Log.e("Main", "$e")
            }
        }
    }

    fun toastMessage(view: View) {
        Toast.makeText(this, getString(R.string.resultCalculation), Toast.LENGTH_LONG)
            .show()
    }
}
package net.mustafabaser.dkscurrencyconversion

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_custom_currency.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONObject
import java.net.URL

class MainActivity : AppCompatActivity() {

    var baseCurrency = "EUR"
    var targetCurrency = "USD"
    var conversionRate = 0f

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        spinnerSetup()
        textChangedStuff()
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

    private fun textChangedStuff() {
        amountOfFirstMoney.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                try {
                    getApiResult()
                } catch (e: Exception) {
                    Toast.makeText(
                        applicationContext,
                        getString(R.string.typeAValue),
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                Log.d("Main", "Before Text Changed")
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                Log.d("Main", "OnTextChanged")
            }
        })
    }

    private fun getApiResult() {
        if (amountOfFirstMoney != null && amountOfFirstMoney.text.isNotEmpty() && amountOfFirstMoney.text.isNotBlank()) {
            //API Document: https://www.frankfurter.app/docs/
            var API =
                "https://api.frankfurter.app/latest?amount=1&from=$baseCurrency&to=$targetCurrency"

            if (baseCurrency == targetCurrency) {
                Toast.makeText(
                    applicationContext,
                    getString(R.string.PickACurrencyToConvert),
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                GlobalScope.launch(Dispatchers.IO) {
                    try {

                        val apiResult = URL(API).readText()
                        val jsonObject = JSONObject(apiResult)
                        conversionRate =
                            jsonObject.getJSONObject("rates").getString(targetCurrency)
                                .toFloat()

                        Log.d("Main", "$conversionRate")
                        Log.d("Main", apiResult)

                        withContext(Dispatchers.Main) {
                            val text =
                                ((amountOfFirstMoney.text.toString()
                                    .toFloat()) * conversionRate).toString()
                            amountOfConvertedMoney?.setText(text)
                        }

                    } catch (e: Exception) {
                        Log.e("Main", "$e")
                    }
                }
            }
        }
    }

    private fun spinnerSetup() {
        val spinner: Spinner = findViewById(R.id.spinner_firstConversion)

        ArrayAdapter.createFromResource(
            this,
            R.array.currencies,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adapter
        }

        spinner.onItemSelectedListener = (object : OnItemSelectedListener {
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


    fun toastMessage(view: View) {
        Toast.makeText(this@MainActivity, getString(R.string.resultCalculation), Toast.LENGTH_LONG)
            .show()
    }

    fun euroSelected(view: View){
        targetCurrency = "EUR"
        selectedCurrency.setText(targetCurrency)
        getApiResult()
        scrollView.smoothScrollTo(0,0)
    }

    fun usdSelected(view: View){
        targetCurrency = "USD"
        selectedCurrency.setText(targetCurrency)
        getApiResult()
        scrollView.smoothScrollTo(0,0)
    }

    fun trySelected(view: View){
        targetCurrency = "TRY"
        selectedCurrency.setText(targetCurrency)
        getApiResult()
        scrollView.smoothScrollTo(0,0)
    }

    fun gbpSelected(view: View){
        targetCurrency = "GBP"
        selectedCurrency.setText(targetCurrency)
        getApiResult()
        scrollView.smoothScrollTo(0,0)
    }

    fun jpySelected(view: View){
        targetCurrency = "JPY"
        selectedCurrency.setText(targetCurrency)
        getApiResult()
        scrollView.smoothScrollTo(0,0)
    }

    fun chfSelected(view: View){
        targetCurrency = "CHF"
        selectedCurrency.setText(targetCurrency)
        getApiResult()
        scrollView.smoothScrollTo(0,0)
    }

    fun cnySelected(view: View){
        targetCurrency = "CNY"
        selectedCurrency.setText(targetCurrency)
        getApiResult()
        scrollView.smoothScrollTo(0,0)
    }

    fun dkkSelected(view: View){
        targetCurrency = "DKK"
        selectedCurrency.setText(targetCurrency)
        getApiResult()
        scrollView.smoothScrollTo(0,0)
    }

    fun audSelected(view: View){
        targetCurrency = "AUD"
        selectedCurrency.setText(targetCurrency)
        getApiResult()
        scrollView.smoothScrollTo(0,0)
    }

    fun cadSelected(view: View){
        targetCurrency = "CAD"
        selectedCurrency.setText(targetCurrency)
        getApiResult()
        scrollView.smoothScrollTo(0,0)
    }

    fun sekSelected(view: View){
        targetCurrency = "SEK"
        selectedCurrency.setText(targetCurrency)
        getApiResult()
        scrollView.smoothScrollTo(0,0)
    }

    fun nokSelected(view: View){
        targetCurrency = "NOK"
        selectedCurrency.setText(targetCurrency)
        getApiResult()
        scrollView.smoothScrollTo(0,0)
    }

    fun rubSelected(view: View){
        targetCurrency = "RUB"
        selectedCurrency.setText(targetCurrency)
        getApiResult()
        scrollView.smoothScrollTo(0,0)
    }

    fun hkdSelected(view: View){
        targetCurrency = "HKD"
        selectedCurrency.setText(targetCurrency)
        getApiResult()
        scrollView.smoothScrollTo(0,0)
    }

    fun idrSelected(view: View){
        targetCurrency = "IDR"
        selectedCurrency.setText(targetCurrency)
        getApiResult()
        scrollView.smoothScrollTo(0,0)
    }

    fun ilsSelected(view: View){
        targetCurrency = "ILS"
        selectedCurrency.setText(targetCurrency)
        getApiResult()
        scrollView.smoothScrollTo(0,0)
    }

    fun inrSelected(view: View){
        targetCurrency = "INR"
        selectedCurrency.setText(targetCurrency)
        getApiResult()
        scrollView.smoothScrollTo(0,0)
    }

    fun mxnSelected(view: View){
        targetCurrency = "MXN"
        selectedCurrency.setText(targetCurrency)
        getApiResult()
        scrollView.smoothScrollTo(0,0)
    }

    fun czkSelected(view: View){
        targetCurrency = "CZK"
        selectedCurrency.setText(targetCurrency)
        getApiResult()
        scrollView.smoothScrollTo(0,0)
    }

    fun sgdSelected(view: View){
        targetCurrency = "SGD"
        selectedCurrency.setText(targetCurrency)
        getApiResult()
        scrollView.smoothScrollTo(0,0)
    }

    fun thbSelected(view: View){
        targetCurrency = "THB"
        selectedCurrency.setText(targetCurrency)
        getApiResult()
        scrollView.smoothScrollTo(0,0)
    }

    fun hrkSelected(view: View){
        targetCurrency = "HRK"
        selectedCurrency.setText(targetCurrency)
        getApiResult()
        scrollView.smoothScrollTo(0,0)
    }

    fun myrSelected(view: View){
        targetCurrency = "MYR"
        selectedCurrency.setText(targetCurrency)
        getApiResult()
        scrollView.smoothScrollTo(0,0)
    }

    fun bgnSelected(view: View){
        targetCurrency = "BGN"
        selectedCurrency.setText(targetCurrency)
        getApiResult()
        scrollView.smoothScrollTo(0,0)
    }

    fun phpSelected(view: View){
        targetCurrency = "PHP"
        selectedCurrency.setText(targetCurrency)
        getApiResult()
        scrollView.smoothScrollTo(0,0)
    }

    fun plnSelected(view: View){
        targetCurrency = "PLN"
        selectedCurrency.setText(targetCurrency)
        getApiResult()
        scrollView.smoothScrollTo(0,0)
    }

    fun zarSelected(view: View){
        targetCurrency = "ZAR"
        selectedCurrency.setText(targetCurrency)
        getApiResult()
        scrollView.smoothScrollTo(0,0)
    }

    fun iskSelected(view: View){
        targetCurrency = "ISK"
        selectedCurrency.setText(targetCurrency)
        getApiResult()
        scrollView.smoothScrollTo(0,0)
    }

    fun brlSelected(view: View){
        targetCurrency = "BRL"
        selectedCurrency.setText(targetCurrency)
        getApiResult()
        scrollView.smoothScrollTo(0,0)
    }

    fun ronSelected(view: View){
        targetCurrency = "RON"
        selectedCurrency.setText(targetCurrency)
        getApiResult()
        scrollView.smoothScrollTo(0,0)
    }

    fun nzdSelected(view: View){
        targetCurrency = "NZD"
        selectedCurrency.setText(targetCurrency)
        getApiResult()
        scrollView.smoothScrollTo(0,0)
    }

    fun krwSelected(view: View){
        targetCurrency = "KRW"
        selectedCurrency.setText(targetCurrency)
        getApiResult()
        scrollView.smoothScrollTo(0,0)
    }

    fun hufSelected(view: View){
        targetCurrency = "HUF"
        selectedCurrency.setText(targetCurrency)
        getApiResult()
        scrollView.smoothScrollTo(0,0)
    }
}
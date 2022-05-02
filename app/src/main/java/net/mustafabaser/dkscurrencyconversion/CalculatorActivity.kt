package net.mustafabaser.dkscurrencyconversion

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_calculator.*
import net.objecthunter.exp4j.ExpressionBuilder


class CalculatorActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calculator)

        supportActionBar!!.title = getString(R.string.hesapMakinesi)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        //Rakamlar
        bir.setOnClickListener { expression("1", true) }
        iki.setOnClickListener { expression("2", true) }
        uc.setOnClickListener { expression("3", true) }
        dort.setOnClickListener { expression("4", true) }
        bes.setOnClickListener { expression("5", true) }
        alti.setOnClickListener { expression("6", true) }
        yedi.setOnClickListener { expression("7", true) }
        sekiz.setOnClickListener { expression("8", true) }
        dokuz.setOnClickListener { expression("9", true) }
        sifir.setOnClickListener { expression("0", true) }
        kusurat.setOnClickListener { expression(".", true) }

        //Operatorler
        toplama.setOnClickListener { expression("+", false) }
        cikarma.setOnClickListener { expression("-", false) }
        carpma.setOnClickListener { expression("*", false) }
        bolme.setOnClickListener { expression("/", false) }
        parantezAc.setOnClickListener { expression("(", false) }
        parantezKapat.setOnClickListener { expression(")", false) }

        temizle.setOnClickListener {
            expression.text = ""
            sonuc.text = ""
        }

        sil.setOnClickListener {
            val string = expression.text.toString()
            if (string.isNotEmpty()) {
                expression.text = string.substring(0, string.length - 1)
            }
            sonuc.text = ""
        }

        esittir.setOnClickListener {
            try {

                val expression = ExpressionBuilder(expression.text.toString()).build()
                val result = expression.evaluate()
                val longResult = result.toLong()
                if (result == longResult.toDouble())
                    sonuc.text = longResult.toString()
                else
                    sonuc.text = result.toString()

            } catch (e: Exception) {
                Log.d("Exception", " message : " + e.message)
            }
        }

    }

    fun expression(string: String, canClear: Boolean) {

        if (sonuc.text.isNotEmpty()) {
            expression.text = ""
        }
        if (canClear) {
            sonuc.text = ""
            expression.append(string)
        } else {
            expression.append(sonuc.text)
            expression.append(string)
            sonuc.text = ""
        }
    }
}

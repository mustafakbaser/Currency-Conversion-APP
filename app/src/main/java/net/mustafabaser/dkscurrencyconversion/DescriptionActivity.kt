package net.mustafabaser.dkscurrencyconversion

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_description.*
import net.objecthunter.exp4j.ExpressionBuilder


class DescriptionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_description)

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

        //Operators
        toplama.setOnClickListener { expression("+", false) }
        cikarma.setOnClickListener { expression("-", false) }
        carpma.setOnClickListener { expression("*", false) }
        tvDivide.setOnClickListener { expression("/", false) }
        tvOpen.setOnClickListener { expression("(", false) }
        tvClose.setOnClickListener { expression(")", false) }

        tvClear.setOnClickListener {
            tvExpression.text = ""
            tvResult.text = ""
        }

        sil.setOnClickListener {
            val string = tvExpression.text.toString()
            if (string.isNotEmpty()) {
                tvExpression.text = string.substring(0, string.length - 1)
            }
            tvResult.text = ""
        }

        esittir.setOnClickListener {
            try {

                val expression = ExpressionBuilder(tvExpression.text.toString()).build()
                val result = expression.evaluate()
                val longResult = result.toLong()
                if (result == longResult.toDouble())
                    tvResult.text = longResult.toString()
                else
                    tvResult.text = result.toString()

            } catch (e: Exception) {
                Log.d("Exception", " message : " + e.message)
            }
        }

    }

    fun expression(string: String, canClear: Boolean) {

        if (tvResult.text.isNotEmpty()) {
            tvExpression.text = ""
        }
        if (canClear) {
            tvResult.text = ""
            tvExpression.append(string)
        } else {
            tvExpression.append(tvResult.text)
            tvExpression.append(string)
            tvResult.text = ""
        }
    }
}

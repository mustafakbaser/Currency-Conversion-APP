package net.mustafabaser.dkscurrencyconversion

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class DescriptionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_description)

        supportActionBar!!.title = getString(R.string.paraBirimleri)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
    }
}
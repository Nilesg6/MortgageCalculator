package com.example.mortgagecalculator

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.mortgagecalculator.databinding.ActivityDataBinding

class DataActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDataBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_data);
        setContentView(binding.root)
        binding.buttonDone.setOnClickListener { view: View ->
            goBack(view)
        }
        updateView()
    }

    fun updateView()
    {
        val rb10 = binding.ten
        val rb15 = binding.fifteen

        if( Mortgage.getYears( ) == 10 ) {
            rb10.setChecked( true );
        } else if( Mortgage.getYears( ) == 15 ) {
            rb15.setChecked( true );
        } // else do nothing (default is 30)
        val rateET = binding.dataRate
        rateET.setText(Mortgage.getRate().toString())
        val amountET = binding.dataAmount
        amountET.setText(Mortgage.getAmount().toString())
    }
    fun updateMortgageObject()
    {
        val p = Prefs(this)
        val amountET = binding.dataAmount
        val rb10 = binding.ten

        val rb15 = binding.fifteen
        var years = 30
        if (rb10.isChecked)
            years = 10
        else if (rb15.isChecked)
            years = 15
        Mortgage.setYears(years)
        val rateET = binding.dataRate
        val rateString:String = rateET.getText().toString()
        val amountString = amountET.text.toString()
        try {
            val amount = amountString.toFloat()
            Mortgage.setAmount(amount)
            val rate: Float = rateString.toFloat()
            Mortgage.setRate(rate)
            p.setPreferences(Mortgage())

        } catch (nfe: NumberFormatException) {
            Mortgage.setAmount(100000.0f)
            Mortgage.setRate(.035f)
        }
    }

    fun goBack(v: View?) {
        updateMortgageObject()
        finish()
        overridePendingTransition(R.anim.fade_in_and_scale, R.anim.slide_from_left)
    }

}

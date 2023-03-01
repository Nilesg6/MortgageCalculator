package com.example.mortgagecalculator

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.mortgagecalculator.databinding.ActivityDataBinding

class DataActivity : AppCompatActivity() {
    private val mortgage: Mortgage = MainActivity.mortgage
    private lateinit var binding: ActivityDataBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_data);
        binding = ActivityDataBinding.inflate(layoutInflater)
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
        val rb30 = binding.thirty



        if( mortgage.getYears( ) == 10 ) {
            rb10.setChecked( true );
            rb15.setSelected( false );
            rb30.setSelected( false );

        } else if( mortgage.getYears( ) == 15 ) {
            rb15.setChecked( true );
            rb10.setSelected( false );
            rb30.setSelected( false );

        } // else do nothing (default is 30)
        val rateET = binding.dataRate
        rateET.setText(mortgage.getRate().toString())
        val amountET = binding.dataAmount
        amountET.setText(mortgage.getAmount().toString())
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
        mortgage.setYears(years)
        val rateET = binding.dataRate
        val rateString:String = rateET.getText().toString()
        val amountString = amountET.text.toString()
        try {

            mortgage.setAmount(amountString.toFloat())
            mortgage.setRate(rateString.toFloat())
            p.setPreferences(mortgage)

        } catch (nfe: NumberFormatException) {
            mortgage.setAmount(100000.0f)
            mortgage.setRate(.035f)
        }
    }

    fun goBack(v: View?) {
        updateMortgageObject()
        finish()
        overridePendingTransition(R.anim.fade_in_and_scale, 0)
    }

    override fun onStart() {
        super.onStart()
        updateView()
    }

}

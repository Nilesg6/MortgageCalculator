package com.example.mortgagecalculator
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.mortgagecalculator.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    val pf:Prefs = Prefs(this)
    companion object{
        val mortgage = Mortgage()
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        pf.getPreferences(mortgage)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
//        setContentView(R.layout.activity_main)


    }



    fun modifyData(view: View) {
        val myIntent = Intent(this, DataActivity::class.java)
        this.startActivity(myIntent)
        overridePendingTransition(R.anim.slide_from_left, 0)
    }

    override fun onStart() {
        super.onStart()
        binding.amount.text = mortgage.getAmount().toString()
        binding.rate.text = mortgage.getRate().toString()
        binding.years.text = mortgage.getYears().toString()
        binding.payment.text = mortgage.formattedMonthlyPayment()
        binding.total.text = mortgage.formattedTotalPayment()


    }


}
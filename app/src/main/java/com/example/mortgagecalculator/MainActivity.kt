package com.example.mortgagecalculator
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

    fun modifyData(view: View) {
        val myIntent = Intent(this, Mortgage.object)
        this.startActivity(myIntent)
    }


}
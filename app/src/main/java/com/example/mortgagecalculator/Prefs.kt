package com.example.mortgagecalculator

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
class Prefs (context1: Context) {
    private var context: Context? = context1
    private var amount:Float=200000.0f
    private var years: Int =15
    private var rate: Float =0.035f

    fun setPreferences(mort: Mortgage) {
        var s: SharedPreferences? =
            context!!.getSharedPreferences("Mortgage", Context.MODE_PRIVATE)
        var editor = s?.edit()
        if (editor != null) {
            editor.putFloat(Mortgage.PREFERENCE_AMOUNT, Mortgage.getAmount())
        }
        if (editor != null) {
            editor.putInt(Mortgage.PREFERENCE_YEARS, Mortgage.getYears())
        }
        if (editor != null) {
            editor.putFloat(Mortgage.PREFERENCE_RATE, Mortgage.getRate())
        }
    }
    fun getPreferences(mort: Mortgage)
    {
        var s: SharedPreferences? =
            context!!.getSharedPreferences("Mortgage", Context.MODE_PRIVATE)
            if (s != null) {
                Mortgage.setYears(s.getInt(Mortgage.PREFERENCE_YEARS, years))
                Mortgage.setAmount(s.getFloat(Mortgage.PREFERENCE_AMOUNT, amount))
                Mortgage.setRate(s.getFloat(Mortgage.PREFERENCE_RATE, rate))
            }
    }
}
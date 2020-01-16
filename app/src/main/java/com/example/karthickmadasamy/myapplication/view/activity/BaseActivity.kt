package com.example.karthickmadasamy.myapplication.view.activity

import android.support.v7.app.AppCompatActivity

import com.example.karthickmadasamy.myapplication.R

/**
 * Created by Karthick.Madasamy on 12/4/2019.
 */

open class BaseActivity : AppCompatActivity() {
    override fun onBackPressed() {

    }

    override fun onResume() {
        super.onResume()
    }

    override fun onPause() {
        super.onPause()
    }

    companion object {
        private val TAG = BaseActivity::class.java.simpleName
    }
}

package com.example.karthickmadasamy.myapplication.view.activity

import android.os.Bundle

import com.example.karthickmadasamy.myapplication.R
import com.example.karthickmadasamy.myapplication.view.fragments.FeederFragment

/**
 * This is a Main class for our app,its redirect to feeder fragment which renders UI
 * Activity which can have multiple fragments
 * Created by Karthick.Madasamy on 12/4/2019.
 */

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if(savedInstanceState==null) {
            supportFragmentManager.beginTransaction().replace(R.id.fragment_container, FeederFragment()).commit()
        }
    }
}

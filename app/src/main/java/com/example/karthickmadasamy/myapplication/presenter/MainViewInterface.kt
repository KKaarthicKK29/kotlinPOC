package com.example.karthickmadasamy.myapplication.presenter

import com.example.karthickmadasamy.myapplication.models.FeederModel

/**
 * Created by Karthick.Madasamy on 12/4/2019.
 */

interface MainViewInterface {
    fun showToast(s: String)
    fun showProgressBar()
    fun hideProgressBar()
    fun displayFeeder(newsResponse: FeederModel)
    fun displayError(s: String)
}


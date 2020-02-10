package com.example.karthickmadasamy.myapplication.network


import com.example.karthickmadasamy.myapplication.models.FeederModel

import io.reactivex.Observable
import retrofit2.http.GET

/**
 * Created by Karthick.Madasamy on 12/4/2019.
 */

interface NetworkInterface {

    @GET("facts.json")
    fun getData() : Observable<FeederModel>
}


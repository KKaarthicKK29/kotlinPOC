package com.example.karthickmadasamy.myapplication.network


import com.example.karthickmadasamy.myapplication.db.FeederEntity
import com.example.karthickmadasamy.myapplication.models.FeederModel

import io.reactivex.Observable
import retrofit2.http.GET

/**
 * Created by Karthick.Madasamy on 12/4/2019.
 */

interface NetworkInterface {
    @get:GET("facts.json")
    val rows: Observable<FeederModel>
}


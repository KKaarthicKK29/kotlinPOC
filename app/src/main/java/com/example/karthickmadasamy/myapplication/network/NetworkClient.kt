package com.example.karthickmadasamy.myapplication.network

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Retrofit technique used to create an HTTP request and also to process the HTTP response from a REST API.
 * Created by Karthick.Madasamy on 12/4/2019.
 */

object NetworkClient {
    private var retrofitService: Retrofit? = null
    private var BASE_URL = "https://dl.dropboxusercontent.com/s/2iodh4vg0eortkl/"

    fun getRetrofitService(): Retrofit? {
        if (retrofitService == null) {
            val builder = OkHttpClient.Builder()
            val okHttpClient = builder.build()
            retrofitService = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(okHttpClient)
                    .build()
        }
        return retrofitService
    }
}

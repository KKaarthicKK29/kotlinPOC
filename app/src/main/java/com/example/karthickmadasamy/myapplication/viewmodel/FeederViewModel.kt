package com.example.karthickmadasamy.myapplication.viewmodel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData

import com.example.karthickmadasamy.myapplication.db.FeederEntity
import com.example.karthickmadasamy.myapplication.repo.FeederRepository

/**
 * Created by Karthick.Madasamy on 12/27/2019.
 */

class FeederViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: FeederRepository


    val allFeeders: LiveData<List<FeederEntity>>
        get() = repository.all

    init {
        repository = FeederRepository(application.applicationContext)

    }



    fun insertAll(entities: List<FeederEntity>) {
        repository.insertAll(entities)
    }
}

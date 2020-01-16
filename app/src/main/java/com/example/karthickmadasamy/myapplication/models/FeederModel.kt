package com.example.karthickmadasamy.myapplication.models

import com.example.karthickmadasamy.myapplication.db.FeederEntity
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Model would only be the gateway to the domain layer or business logic.
 * FeederModel which helps to fetch the respective node from the api
 * Title is the primary node which has the secondary node called rows
 * Created by Karthick.Madasamy on 12/4/2019.
 */

class FeederModel {

    @SerializedName("title")
    @Expose
    var title: String? = null

    @SerializedName("rows")
    @Expose
    var rows: List<FeederEntity>? = null

}


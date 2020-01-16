package com.example.karthickmadasamy.myapplication.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * title,description,imageHref are the key and value pair in the row node
 * Created by Karthick.Madasamy on 12/4/2019.
 */

class Rows {

    @SerializedName("title")
    @Expose
    var title: String? = null

    @SerializedName("description")
    @Expose
    var description: String? = null

    @SerializedName("imageHref")
    @Expose
    val imageHref: String? = null

}


package com.example.karthickmadasamy.myapplication.db

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.support.annotation.NonNull

/**
 * Created by Karthick.Madasamy on 12/27/2019.
 */
@Entity(tableName = "feeder_table")
class FeederEntity {

    //@PrimaryKey(autoGenerate = true)
    var id: Int = 0
    @PrimaryKey
    @NonNull
    var title: String? = ""
    var description: String? = null

    var imageHref: String? = null
}

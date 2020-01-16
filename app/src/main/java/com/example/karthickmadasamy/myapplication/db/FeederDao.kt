package com.example.karthickmadasamy.myapplication.db

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query

import android.arch.persistence.room.OnConflictStrategy.REPLACE

/**
 * Created by Karthick.Madasamy on 12/27/2019.
 */

@Dao
interface FeederDao {

    @get:Query("select * FROM feeder_table")
    val all: LiveData<List<FeederEntity>>

    @get:Query("select * FROM feeder_table")
    val listData: List<FeederEntity>

    @Insert(onConflict = REPLACE)
    fun insert(entity: FeederEntity)

    @Insert(onConflict = REPLACE)
    fun insertAll(entity: List<FeederEntity>)

    @Query("DELETE FROM feeder_table")
    fun deleteAll()

}

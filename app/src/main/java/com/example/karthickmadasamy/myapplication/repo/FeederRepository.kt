package com.example.karthickmadasamy.myapplication.repo

import android.arch.lifecycle.LiveData
import android.content.Context
import android.os.AsyncTask

import com.example.karthickmadasamy.myapplication.db.DbHelper
import com.example.karthickmadasamy.myapplication.db.FeederDao
import com.example.karthickmadasamy.myapplication.db.FeederEntity

/**
 * Created by Karthick.Madasamy on 12/27/2019.
 */

class FeederRepository(context: Context) {

    private var dbHelper: DbHelper

    val listData:List<FeederEntity>
    get()=dbHelper.feederDao.listData

    val all: LiveData<List<FeederEntity>>
        get() = dbHelper.feederDao.all

    init {
        dbHelper = DbHelper.getInstance(context)
    }


    fun insert(entity: FeederEntity) {
        InsertNoteAsyncTask(dbHelper.feederDao).execute(entity)
    }

    fun insertAll(entities: List<FeederEntity>) {
        InsertAllNoteAsyncTask(dbHelper.feederDao).execute(entities)
    }

    private inner class InsertNoteAsyncTask(private val dao: FeederDao) : AsyncTask<FeederEntity, Unit, Unit>() {
        override fun doInBackground(vararg feederDaos: FeederEntity): Unit? {
            dao.insert(feederDaos[0])
            return null
        }

    }

    private inner class InsertAllNoteAsyncTask(private val dao: FeederDao) : AsyncTask<List<FeederEntity>, Unit, Unit>() {
        override fun doInBackground(vararg feederDaos: List<FeederEntity>): Unit? {
            dao.insertAll(feederDaos[0])
            return null
        }

    }

}

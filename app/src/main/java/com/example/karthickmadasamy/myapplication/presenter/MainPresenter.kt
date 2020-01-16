package com.example.karthickmadasamy.myapplication.presenter

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.util.Log

import com.example.karthickmadasamy.myapplication.models.FeederModel
import com.example.karthickmadasamy.myapplication.network.NetworkClient
import com.example.karthickmadasamy.myapplication.network.NetworkInterface
import com.example.karthickmadasamy.myapplication.view.fragments.FeederFragment
import com.example.karthickmadasamy.myapplication.viewmodel.FeederViewModel

import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.annotations.NonNull
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers



/**
 * RxJava and RxAndroid technique used in this project
 * With the help of Observables and Schedulers,plays major role in supporting multithreading concept in android applications.
 * Observable is a data stream that do some work and emits data.
 * Observer/Schedulers is the counter part of Observable. It receives the data emitted by Observable.
 * Created by Karthick.Madasamy on 12/4/2019.
 */

class MainPresenter(private val fragment: FeederFragment, var ctx: Context) : MainPresenterInterface {
    private val TAG = this@MainPresenter.javaClass.name
    public var feederViewModel: FeederViewModel

    val observable: Observable<FeederModel>
        get() = NetworkClient.getRetrofitService()!!.create(NetworkInterface::class.java)
                .rows
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())


    val observer: DisposableObserver<FeederModel>
        get() = object : DisposableObserver<FeederModel>() {

            override fun onNext(@NonNull model: FeederModel) {
                model.rows!!.stream().forEach { e ->
                    if (e.title == null) {
                        e.title = "sample"
                    }
                }
                fragment.toolbar!!.title = model.title
                feederViewModel.insertAll(model.rows!!)

            }

            override fun onError(@NonNull e: Throwable) {}
            override fun onComplete() {
                fragment.hideProgressBar()

            }
        }

    init {

        feederViewModel = ViewModelProviders.of(fragment).get(FeederViewModel::class.java)
        feederViewModel.allFeeders.observe(fragment, Observer{ t ->
            fragment.adapter!!.feederList= t!!
        })
    }

    override fun getRows() {
        observable.subscribeWith(observer)
    }
}



package com.example.karthickmadasamy.myapplication.presenter

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders

import com.example.karthickmadasamy.myapplication.models.FeederModel
import com.example.karthickmadasamy.myapplication.network.NetworkClient
import com.example.karthickmadasamy.myapplication.network.NetworkInterface
import com.example.karthickmadasamy.myapplication.view.fragments.FeederFragment
import com.example.karthickmadasamy.myapplication.viewmodel.FeederViewModel

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.annotations.NonNull
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers


/**
 * RxJava and RxAndroid technique used in this project
 * With the help of Observables and Schedulers,plays major role in supporting multithreading concept in android applications.
 * Observable is a data stream that do some work and emits data.
 * Observer/Schedulers is the counter part of Observable. It receives the data emitted by Observable.
 * Created by Karthick.Madasamy on 12/4/2019.
 */

class MainPresenter(private val fragment: FeederFragment) : MainPresenterInterface {
    private var feederViewModel: FeederViewModel
    private var mCompositeDisposable: CompositeDisposable? = null

    init {
        feederViewModel = ViewModelProviders.of(fragment).get(FeederViewModel::class.java)
    }

    fun initObserver() {
        feederViewModel.allFeeders.observe(fragment, Observer { t ->
            fragment.adapter!!.feederList = t!!
        })
        fragment.hideProgressBar()
    }

    override fun getRows() {
        handleRetrofitCall()
    }

    private fun handleRetrofitCall() {

        mCompositeDisposable = CompositeDisposable()

        val requestInterface = NetworkClient.getRetrofitService()!!.create(NetworkInterface::class.java)


        mCompositeDisposable?.add(requestInterface.getData()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleResponse, this::handleError))
        fragment.showProgressBar()

    }

    private fun handleResponse(@NonNull model: FeederModel) {
        model.rows!!.stream().forEach { e ->
            if (e.title == null) {
                e.title = "sample"
            }
        }
        fragment.toolbar.title = model.title
        feederViewModel.insertAll(model.rows!!)
        feederViewModel.allFeeders
        fragment.hideProgressBar()

    }

    private fun handleError(error: Throwable) {
        fragment.hideProgressBar()

    }
}



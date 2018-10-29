package com.example.alexey.test.mvp

import android.util.Log
import com.example.alexey.test.models.Stock
import com.example.alexey.test.utils.Api
import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit
import io.reactivex.disposables.CompositeDisposable



class ListPresenter: MvpBasePresenter<ListView>() {

    private val api = Api.create()

    private val subscribe = api.getWetherList()
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())

    private val compositeDisposable = CompositeDisposable()
    private val PERIOD:Long=15
    private var loading=false
    private var sendData={ data:Stock ->
        loading=false
        ifViewAttached { view ->
            view.onUpdate(data.stock)
        }
    }


    override fun attachView(view: ListView) {
        super.attachView(view)
        runTimer()
    }

    private fun runTimer(){
        compositeDisposable.add(
            Observable.interval(PERIOD, TimeUnit.SECONDS,Schedulers.io())
                .timeInterval()
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap { Log.d("Timer","sent request");subscribe;}
                .subscribe(
                    {sendData},
                    {e -> Log.d("Timer","$e");loading=false  },
                    {Log.d("Timer","complited");loading=false}
                ))
    }




    private fun fetchData(){
        if (!loading){
            loading=true
            compositeDisposable.add(subscribe
                .subscribe(sendData,
                    {loading=false},
                    {loading=false}))
        }else{
            Log.d("Timer","Disable request")
        }
    }

    fun updateData(){
        //compositeDisposable.dispose()
        //runTimer()
        fetchData()

    }

    override fun detachView() {
        compositeDisposable.dispose()
        super.detachView()
    }

}
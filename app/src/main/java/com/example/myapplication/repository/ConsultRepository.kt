package com.example.myapplication.repository

import android.annotation.SuppressLint
import com.example.myapplication.utils.Server
import com.example.myapplication.viewmodel.BaseMutableLiveData
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class ConsultRepository<R> {

    private lateinit var subscribe: Observable<R>


    fun getData(mutableLiveData: BaseMutableLiveData<R>, fechai:String, fechaf :String) {
        subscribe = Server().onAttemptgetConsul(fechai,fechaf )as Observable<R>
        execute(mutableLiveData)
    }

    @SuppressLint("CheckResult")
    fun execute(mutableLiveData: BaseMutableLiveData<R>) {
        subscribe.observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io())
            .subscribe({ result ->
                if (result is Throwable)
                    mutableLiveData.error = result.message!!
                else
                    mutableLiveData.value = result
            }, { error -> mutableLiveData.error = error.message!! })
    }

}
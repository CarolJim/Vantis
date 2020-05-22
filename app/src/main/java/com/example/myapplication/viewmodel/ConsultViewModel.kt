package com.example.myapplication.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.model.Consult
import com.example.myapplication.repository.ConsultRepository

class ConsultViewModel(aplication: Application): AndroidViewModel(aplication) {


    var mutableData:BaseMutableLiveData<Consult> = BaseMutableLiveData()

    fun getConstul(fechai:String,fechaf:String){
        mutableData.inProgress="obteniendo datos"
        ConsultRepository<Consult>().getData(mutableData,fechai,fechaf)

    }

}



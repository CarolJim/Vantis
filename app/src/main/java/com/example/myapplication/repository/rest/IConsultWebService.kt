package com.example.myapplication.repository.rest

import com.example.myapplication.model.Consult
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface IConsultWebService {
    @GET()
    @Headers("content-type: application/json;charset=UTF-8")
    fun getConsultRepo(@Body body: RequestBody, @Header("idUser") idUser: String, @Query("dateStarFind") dateStarFind: String, @Query("dateEndFind") dateEndFind: String): Call<Consult>




}
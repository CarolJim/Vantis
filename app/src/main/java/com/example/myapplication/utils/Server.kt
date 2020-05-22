package com.example.myapplication.utils

import android.util.Log
import com.example.myapplication.BasicAuthInterceptor
import com.example.myapplication.model.Consult
import com.example.myapplication.repository.rest.IConsultWebService
import com.google.gson.Gson
import io.reactivex.Observable
import okhttp3.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException


class Server {

    val client =  OkHttpClient.Builder()
        .addInterceptor(BasicAuthInterceptor("checkin_vantis", "V4nt1s.$18"))
        .build()

    private val request = Retrofit.Builder()
        .baseUrl("http://35.236.88.26:8074/vantischeckin-services/rest/History/")
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()


    fun onAttemptgetConsul(fechai:String,fechaf:String): Observable<Consult> {
        return Observable.create { emitter ->
            val text = "d=" + Gson().toJson(request)
            val body = RequestBody.create(MediaType.parse("text/plain"), text)

                request?.create(IConsultWebService::class.java)?.getConsultRepo(body,"379",fechai,fechaf)?.enqueue(
                    object : Callback<Consult> {
                        override fun onResponse(call: Call<Consult?>, response: Response<Consult?>) {

                            emitter.onNext(response as Consult)
                        }

                        override fun onFailure(call: Call<Consult?>, t: Throwable) {
                            Log.e(
                                "Consultar",
                                "Ocurrió un error al obtener la información del servidor",
                                t
                            )

                        }
                    })
            }

        }
    }

package com.example.myapplication.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Report(

    @Json(name = "idCheckRegister")
    var idCheckRegister: Int? = null,
    @Json(name = "idUser")
    var idUser: Int? = null,
    @Json(name = "checkDate")
    var checkDate: String? = null,
    @Json(name = "idCheckType")
    var idCheckType: Int? = null,
    @Json(name = "deviceToken")
    var deviceToken: String? = null,
    @Json(name = "serverDate")
    var serverDate: String? = null,
    @Json(name = "checkLatLong")
    var checkLatLong: String? = null,
    @Json(name = "checkComments")
    var checkComments: String? = null,
    @Json(name = "idUserAssignment")
    var idUserAssignment: Int? = null,
    @Json(name = "idCheckReasons")
    var idCheckReasons: Int? = null,
    @Json(name = "idLastCheck")
    var idLastCheck: Int? = null

)
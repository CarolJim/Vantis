package com.example.myapplication.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class Consult (
    @Json(name = "codeMessage")
    val codeMessage: String? = null,

    @Json(name = "responseObject")
    val responseObject: Array<Report>? = null,

    @Json(name = "responseMessage")
    val responseMessage: String? = null

) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Consult

        if (responseObject != null) {
            if (other.responseObject == null) return false
            if (!responseObject.contentEquals(other.responseObject)) return false
        } else if (other.responseObject != null) return false

        return true
    }

    override fun hashCode(): Int {
        return responseObject?.contentHashCode() ?: 0
    }
}







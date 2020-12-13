package com.purpleshade.pms.utils

import android.util.Base64
import android.util.Log
import org.json.JSONObject
import java.io.UnsupportedEncodingException
import java.nio.charset.Charset

/**
 * Created by pawan on 13,December,2020
 */
object JWTUtils {
    val charset: Charset = Charsets.UTF_8
    var tokenDetail = ""
    var userId = ""

    @Throws(Exception::class)
    fun decoded(JWTEncoded: String) {
        try {
            val split = JWTEncoded.split("\\.".toRegex()).toTypedArray()
            //   Log.d("JWT_DECODED", "Header: " + getJson(split[0]))
            Log.d("JWT_DECODED", getJson(split[1]))
            tokenDetail = getJson(split[1])
        } catch (e: UnsupportedEncodingException) {
            //Error
        }
    }

    @Throws(UnsupportedEncodingException::class)
    fun getJson(strEncoded: String): String {
        val decodedBytes: ByteArray = Base64.decode(strEncoded, Base64.URL_SAFE)
        return String(decodedBytes, charset)
    }

    fun parseUserDetail() {
        val reader = JSONObject(tokenDetail)
        val userDetail = reader.getString("userId")
        userId = userDetail
    }
}
package com.purpleshade.pms.utils

import android.util.Base64
import android.util.Log
import java.io.UnsupportedEncodingException
import java.nio.charset.Charset

/**
 * Created by pawan on 13,December,2020
 */
object JWTUtils {
    val charset: Charset = Charsets.UTF_8

    @Throws(Exception::class)
    fun decoded(JWTEncoded: String) {
        try {
            val split = JWTEncoded.split("\\.".toRegex()).toTypedArray()
         //   Log.d("JWT_DECODED", "Header: " + getJson(split[0]))
            Log.d("JWT_DECODED", getJson(split[1]))
        } catch (e: UnsupportedEncodingException) {
            //Error
        }
    }

    @Throws(UnsupportedEncodingException::class)
    private fun getJson(strEncoded: String): String {
        val decodedBytes: ByteArray = Base64.decode(strEncoded, Base64.URL_SAFE)
        return String(decodedBytes, charset)
    }
}
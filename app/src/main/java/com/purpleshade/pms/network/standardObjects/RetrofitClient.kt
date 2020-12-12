package com.purpleshade.pms.network.standardObjects

import com.purpleshade.pms.network.ApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    var apiURL: String = ""
    lateinit var apiService: ApiService

    fun initRosAPI(baseURLs: String) {
        apiURL = baseURLs
        val retrofitInstance = Retrofit.Builder()
                .baseUrl(apiURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        apiService = retrofitInstance.create(ApiService::class.java)
    }

}


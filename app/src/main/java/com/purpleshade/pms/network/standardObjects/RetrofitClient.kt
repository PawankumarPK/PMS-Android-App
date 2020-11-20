package com.purpleshade.pms.network.standardObjects

import com.purpleshade.pms.network.ApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    var rosURL: String = ""
    lateinit var rosService: ApiService

    fun initRosAPI(baseURLs: String) {
        rosURL = baseURLs
        val retrofitInstance = Retrofit.Builder()
                .baseUrl(rosURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        rosService = retrofitInstance.create(ApiService::class.java)
    }

}


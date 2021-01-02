package com.purpleshade.pms.network

import com.purpleshade.pms.model.SignUpModel
import com.purpleshade.pms.model.Records
import com.purpleshade.pms.model.UpdateRecord
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

/**
 * Created by pawan on 19,November,2020
 */
interface ApiService {

    @FormUrlEncoded
    @POST("/user/login")
    fun login(@Field("email") email: String, @Field("password") password: String): Call<SignUpModel>

    @FormUrlEncoded
    @POST("/user/signup")
    fun signUp(
        @Field("username") username: String, @Field("email") email: String,
        @Field("password") password: String, @Field("confirmPassword") confmPass: String
    ): Call<SignUpModel>

    @GET("/records/allRecords")
    fun allRecords(@Query("id") id: String): Call<Records>

    @FormUrlEncoded
    @POST("records/inputRecord")
    fun addRecordDetail(
        @Field("title") title: String,
        @Field("webAddress") webAddress: String,
        @Field("email") email: String,
        @Field("password") password: String,
        @Field("addNote") addNote: String
    ): Call<SignUpModel>

    @DELETE("records/deleteRecord")
    fun deleteRecord(@Query("id") id: String): Call<SignUpModel>

    @PATCH("records/updateDetails")
    fun updateRecord(
        @Query("id") id: String, @Body post: UpdateRecord
    ): Call<SignUpModel>


    @GET("records/recordDetail")
    fun recordDetail(@Query("id") id: String): Call<Records>

    companion object {
        operator fun invoke(): ApiService {
            return Retrofit.Builder()
                .baseUrl("http://192.168.0.109:3000")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ApiService::class.java)
        }
    }
}
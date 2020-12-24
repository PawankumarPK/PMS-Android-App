package com.purpleshade.pms.network

import com.purpleshade.pms.network.signupModel.SignUpModel
import com.purpleshade.pms.utils.Records
import retrofit2.Call
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
    fun deleteRecord(@Query("id") id: String) : Call<SignUpModel>
/*

    @PATCH("updateDetails")
    fun updateRecord(@Query("id") id:String) : Call<RecordDetails>
*/


    @GET("records/recordDetail")
    fun recordDetail(@Query("id") id:String) : Call<Records>
}
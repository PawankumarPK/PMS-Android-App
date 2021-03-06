package com.purpleshade.pms.network

import com.purpleshade.pms.model.*
import com.purpleshade.pms.utils.Helper
import okhttp3.OkHttpClient
import okhttp3.ResponseBody
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
        @Field("password") password: String, @Field("confirmPassword") confirmPass: String
    ): Call<ResponseBody>

    @GET("/records/allRecords")
    fun allRecords(@Query("id") id: String): Call<Records>

    @GET("/records/recordDetail")
    fun recordDetail(@Query("id") id: String): Call<Records>

    @GET("/user/userProfile")
    fun userProfile(@Query("id") id: String): Call<Profile>

    @FormUrlEncoded
    @POST("/records/inputRecord")
    fun addRecordDetail(
        @Field("title") title: String,
        @Field("webAddress") webAddress: String,
        @Field("email") email: String,
        @Field("password") password: String,
        @Field("addNote") addNote: String,
        @Field("loginId") loginId: String
    ): Call<SignUpModel>

    @DELETE("/records/deleteRecord")
    fun deleteRecord(@Query("id") id: String): Call<SignUpModel>

    @PATCH("/records/updateDetails")
    fun updateRecord(@Query("id") id: String, @Body post: UpdateRecord): Call<ResponseBody>

    @PATCH("/user/updateProfile")
    fun updateProfile(@Query("id") id: String, @Body post: UpdateProfile): Call<SignUpModel>

    @GET("/user/signUpVerify")
    fun verifySignUp(@Query("email") email: String, @Query("token") token: String): Call<ResponseBody>

    @FormUrlEncoded
    @POST("/user/forgotPassword")
    fun forgotPassword(@Field("email") email: String): Call<ResponseBody>

    @FormUrlEncoded
    @POST("/user/verificationToken")
    fun verificationToken(@Field("email") email: String): Call<VerificationModel>

    @FormUrlEncoded
    @POST("/user/verificationSignUpToken")
    fun verificationSignUpToken(@Field("email") email: String): Call<SignUpVerificationModel>


    @GET("/user/forgotPassVerify")
    fun forgotPassVerify(@Query("email") email: String, @Query("token") token: String): Call<ResponseBody>

    @FormUrlEncoded
    @POST("/user/removeForgotPassField")
    fun removeForgotPassField(@Field("email") email: String): Call<ResponseBody>

    @FormUrlEncoded
    @POST("/user/removeSignUp")
    fun removeSignUp(@Field("email") email: String): Call<ResponseBody>

    @FormUrlEncoded
    @POST("/user/updatePassword")
    fun updatePassword(@Field("email") email: String, @Field("password") password: String): Call<ResponseBody>

    @PATCH("/user/logout")
    fun logout(@Query("id") id: String): Call<ResponseBody>

    @FormUrlEncoded
    @POST("/user/updateAppPin")
    fun updateAppPin(@Field("email") id: String, @Field("pin") pin: String): Call<ResponseBody>



    /*companion object {
        operator fun invoke(): ApiService {
            return Retrofit.Builder()
                .baseUrl("")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ApiService::class.java)
        }
    }*/
}
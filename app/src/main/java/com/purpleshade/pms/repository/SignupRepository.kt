package com.purpleshade.pms.repository

import android.content.Context
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.purpleshade.pms.model.SignUpModel
import com.purpleshade.pms.network.RetrofitClient
import kotlinx.android.synthetic.main.sign_up_fragment.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by pawan on 12,January,2021
 */
class SignupRepository {

     fun signUp(username: String, email: String, password: String, confirmPass: String,context:Context,progressBar:MutableLiveData<Boolean>): LiveData<String> {
        val signUpResponse = MutableLiveData<String>()
        val api = RetrofitClient.apiService
        val call = api.signUp(username, email, password, confirmPass)

        call.enqueue(object : Callback<SignUpModel> {
            override fun onFailure(call: Call<SignUpModel>?, t: Throwable?) {
                Toast.makeText(context, "Something went wrong", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<SignUpModel>, response: Response<SignUpModel>) {
                progressBar.postValue(false)
                Toast.makeText(context, "User Register Successfully", Toast.LENGTH_SHORT).show()
            }
        })
        return signUpResponse
    }

}
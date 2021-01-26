package com.purpleshade.pms.repository

import android.content.Context
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.navigation.findNavController
import com.purpleshade.pms.R
import com.purpleshade.pms.model.SignUpModel
import com.purpleshade.pms.network.RetrofitClient
import com.purpleshade.pms.utils.JWTUtils
import com.purpleshade.pms.utils.customObject.UserResponse
import com.purpleshade.pms.utils.hide
import com.purpleshade.pms.utils.snackbar
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by pawan on 02,January,2021
 */
class LoginRepository {

    fun doLogin(email: String, password: String, context: Context, view: View, progressBar:ProgressBar): LiveData<String> {
        val loginResponse = MutableLiveData<String>()
        val api = RetrofitClient.apiService
        val call = api.login(email, password)

        call.enqueue(object : Callback<SignUpModel> {
            override fun onFailure(call: Call<SignUpModel>?, t: Throwable?) {
                loginResponse.value = t?.message
                progressBar.hide()
            }

            override fun onResponse(call: Call<SignUpModel>, response: Response<SignUpModel>) {
                if (response.isSuccessful) {
                    loginResponse.value = response.body()!!.message
                    UserResponse.response = response.body()!!.message
                    val token = response.body()!!.token.toString()
                    JWTUtils.decoded(token)
                    JWTUtils.parseUserDetail()
                    if (UserResponse.response.equals("User Found")) {
                        progressBar.hide()
                        view.findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
                    }
                } else {
                    progressBar.hide()
                    view.snackbar("Invalid Credential")
                }
            }
        })


        return loginResponse
    }
}
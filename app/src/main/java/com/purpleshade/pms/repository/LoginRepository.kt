package com.purpleshade.pms.repository

import android.content.Context
import android.view.View
import android.widget.Toast
import androidx.databinding.ObservableInt
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.navigation.findNavController
import com.purpleshade.pms.R
import com.purpleshade.pms.model.SignUpModel
import com.purpleshade.pms.network.RetrofitClient
import com.purpleshade.pms.utils.JWTUtils
import com.purpleshade.pms.utils.customInterface.AuthListener
import com.purpleshade.pms.utils.customObject.UserResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by pawan on 02,January,2021
 */
class LoginRepository {

    fun doLogin(email: String, password: String, progressBar: ObservableInt?, context: Context, view: View): LiveData<String> {
        val loginResponse = MutableLiveData<String>()
        val api = RetrofitClient.apiService
        val call = api.login(email, password)

        call.enqueue(object : Callback<SignUpModel> {
            override fun onFailure(call: Call<SignUpModel>?, t: Throwable?) {
                loginResponse.value = t?.message
            }

            override fun onResponse(call: Call<SignUpModel>, response: Response<SignUpModel>) {
                if (response.isSuccessful) {
                    loginResponse.value = response.body()!!.message
                    UserResponse.response = response.body()!!.message
                    val token = response.body()!!.token.toString()
                    JWTUtils.decoded(token)
                    JWTUtils.parseUserDetail()
                    if (UserResponse.response.equals("User Found")) {
                        progressBar!!.set(View.GONE)
                        view.findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
                    }
                } else {
                    progressBar!!.set(View.GONE)
                    Toast.makeText(context, "InValid Credential", Toast.LENGTH_SHORT).show()
                }
            }
        })


        return loginResponse
    }
}
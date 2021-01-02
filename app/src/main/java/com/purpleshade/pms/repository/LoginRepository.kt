package com.purpleshade.pms.repository

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.purpleshade.pms.R
import com.purpleshade.pms.model.SignUpModel
import com.purpleshade.pms.network.ApiService
import com.purpleshade.pms.network.RetrofitClient
import com.purpleshade.pms.network.RetrofitClient.apiService
import com.purpleshade.pms.utils.JWTUtils
import com.purpleshade.pms.utils.customObject.UserResponse
import kotlinx.android.synthetic.main.login_fragment.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by pawan on 02,January,2021
 */
class LoginRepository {

     fun doLogin(email: String, password: String): LiveData<String> {
        val loginResponse = MutableLiveData<String>()
        val api = ApiService()
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


                    /*if (res.equals("User Found"))
                        findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
                    else
                        Toast.makeText(baseActivity, "Invalid credential", Toast.LENGTH_SHORT).show()*/
                }

            }
        })

        return loginResponse
    }
}
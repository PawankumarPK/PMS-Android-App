package com.purpleshade.pms.repository

import android.content.Context
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.navigation.findNavController
import com.purpleshade.pms.R
import com.purpleshade.pms.activity.BaseActivity
import com.purpleshade.pms.db.RoomUser
import com.purpleshade.pms.model.SignUpModel
import com.purpleshade.pms.network.RetrofitClient
import com.purpleshade.pms.utils.JWTUtils
import com.purpleshade.pms.utils.customObject.Flag
import com.purpleshade.pms.utils.customObject.UserResponse
import com.purpleshade.pms.utils.hide
import com.purpleshade.pms.utils.snackbar
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception

/**
 * Created by pawan on 02,January,2021
 */
class LoginRepository {

    var id: Int? = null

    fun doLogin(context: Context, email: String, password: String, user: RoomUser, view: View, progressBar: ProgressBar): LiveData<String> {
        val loginResponse = MutableLiveData<String>()
        val api = RetrofitClient.apiService
        val call = api.login(email, password)

        call.enqueue(object : Callback<SignUpModel> {
            override fun onFailure(call: Call<SignUpModel>?, t: Throwable?) {
                loginResponse.value = t?.message
                view.snackbar(context, context.getString(R.string.something_went_wrong), R.color.colorWarning)
                progressBar.hide()
            }

            override fun onResponse(call: Call<SignUpModel>, response: Response<SignUpModel>) {
                if (response.isSuccessful) {
                    loginResponse.value = response.body()!!.message
                    UserResponse.response = response.body()!!.message
                    val token = response.body()!!.token.toString()
                    JWTUtils.decoded(token)
                    JWTUtils.parseUserDetail()

                    user.userId = JWTUtils.userId
                    user.token = token
                    user.username = JWTUtils.userName
                    user.email = JWTUtils.userEmail
                    //insert data into table


                    Log.d("----->>",user.token.toString())

                    try {
                        id = BaseActivity.INSTANCE!!.myDao().user.id!!
                    } catch (e: Exception) {

                    }

                    //If no user is there then store user detail in room db else update user when user logged out
                    if (id == null)
                        BaseActivity.INSTANCE!!.myDao().userDetails(user)
                    else
                        BaseActivity.INSTANCE!!.myDao().userDetailsUpdate(JWTUtils.userId, JWTUtils.userName, JWTUtils.userEmail,token, 1)

                    if (UserResponse.response.equals("User Found")) {
                        progressBar.hide()
                        view.findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
                    }
                } else {
                    progressBar.hide()
                    view.snackbar(context, context.getString(R.string.invalid_credential), R.color.colorWarning)
                }
            }
        })


        return loginResponse
    }
}

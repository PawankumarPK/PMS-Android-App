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
import com.purpleshade.pms.utils.hide
import com.purpleshade.pms.utils.snackbar
import com.purpleshade.pms.utils.toast
import kotlinx.android.synthetic.main.sign_up_fragment.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by pawan on 12,January,2021
 */
class SignupRepository {
    var view : View? = null

     fun signUp(username: String, email: String, password: String, confirmPass: String,context:Context,progressBar:ProgressBar): LiveData<String> {
        val signUpResponse = MutableLiveData<String>()
        val api = RetrofitClient.apiService
        val call = api.signUp(username, email, password, confirmPass)

        call.enqueue(object : Callback<SignUpModel> {
            override fun onFailure(call: Call<SignUpModel>?, t: Throwable?) {
                view!!.snackbar(context,context.getString(R.string.something_went_wrong), R.color.colorWarning)
                progressBar.hide()
            }

            override fun onResponse(call: Call<SignUpModel>, response: Response<SignUpModel>) {
              //  view!!.snackbar(context,context.getString(R.string.register_successful), R.color.colorGreen)
                progressBar.hide()
                view!!.findNavController().navigate(R.id.action_signUpFragment_to_verificationFragment)
            }
        })
        return signUpResponse
    }

}
package com.purpleshade.pms.repository

import android.content.Context
import android.util.Log
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.purpleshade.pms.R
import com.purpleshade.pms.model.SignUpModel
import com.purpleshade.pms.network.RetrofitClient
import com.purpleshade.pms.utils.customObject.Flag
import com.purpleshade.pms.utils.snackbar
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by pawan on 27,March,2021
 */
class SignupVerificationRepository {
    var view: View? = null

    fun verifySignUp(context: Context): LiveData<String> {
        val verificationResponse = MutableLiveData<String>()
        val api = RetrofitClient.apiService
        val call = api.verifySignUp(Flag.userSignUpEmail, Flag.signUpToken)

        call.enqueue(object : Callback<ResponseBody> {

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                view!!.snackbar(context, context.getString(R.string.something_went_wrong), R.color.colorWarning)
            }

            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                Log.d("======>>>",Flag.userSignUpEmail +" " +  Flag.signUpToken)
                view!!.snackbar(context, context.getString(R.string.register_successful), R.color.colorGreen)

            }


        })

        return verificationResponse
    }

}
package com.purpleshade.pms.repository

import android.content.Context
import android.util.Log
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.navigation.findNavController
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
class ForgotPasswordRepository {
    var view: View? = null

    fun sendEmail(context: Context): LiveData<String> {
        val verificationResponse = MutableLiveData<String>()
        val api = RetrofitClient.apiService
        val call = api.forgotPassword(Flag.forgotPassEmail)

        call.enqueue(object : Callback<ResponseBody> {

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                view!!.snackbar(context, context.getString(R.string.something_went_wrong), R.color.colorWarning)
            }

            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                Log.d("====>>",Flag.forgotPassEmail)
                view!!.snackbar(context, "Check email", R.color.colorGreen)
                view!!.findNavController().navigate(R.id.action_forgotPasswordFragment_to_fpVerificationFragment)

            }


        })

        return verificationResponse
    }

}
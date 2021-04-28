package com.purpleshade.pms.repository

import android.content.Context
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.navigation.findNavController
import com.purpleshade.pms.R
import com.purpleshade.pms.model.VerificationModel
import com.purpleshade.pms.network.RetrofitClient
import com.purpleshade.pms.utils.customObject.Flag
import com.purpleshade.pms.utils.hide
import com.purpleshade.pms.utils.snackbar
import com.purpleshade.pms.utils.toast
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by pawan on 28,March,2021
 */
class FpVerificationRepository {


    fun getVerificationTokenForMatch(view: View, context: Context, progressBar: ProgressBar): LiveData<String> {
        val verificationResponse = MutableLiveData<String>()
        val api = RetrofitClient.apiService
        val call = api.verificationToken(Flag.forgotPassEmail)

        call.enqueue(object : Callback<VerificationModel> {

            override fun onFailure(call: Call<VerificationModel>, t: Throwable) {
                progressBar.hide()
                view!!.snackbar(context, context.getString(R.string.something_went_wrong), R.color.colorWarning)
            }

            override fun onResponse(call: Call<VerificationModel>, response: Response<VerificationModel>) {
                progressBar.hide()
                Flag.forgotPassToken = response.body()!!.token.forgotPassToken.toString()

            }
        })

        return verificationResponse
    }


    fun verifyForgetPasswordCode(view: View, context: Context, progressBar: ProgressBar): LiveData<String> {
        val verificationResponse = MutableLiveData<String>()
        val api = RetrofitClient.apiService
        val call = api.forgotPassVerify(Flag.forgotPassEmail, Flag.forgotPassToken)

        call.enqueue(object : Callback<ResponseBody> {

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                progressBar.hide()
                view!!.snackbar(context, context.getString(R.string.something_went_wrong), R.color.colorWarning)
            }

            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                progressBar.hide()
                view!!.snackbar(context, context.getString(R.string.yor_are_successfully_verified_the_account), R.color.colorGreen)
                view!!.findNavController().navigate(R.id.action_fpVerificationFragment_to_createNewPassFragment)

            }


        })

        return verificationResponse
    }


    fun resendVerificationCode(view: View, context: Context, progressBar: ProgressBar): LiveData<String> {
        val verificationResponse = MutableLiveData<String>()
        val api = RetrofitClient.apiService
        val call = api.forgotPassword(Flag.forgotPassEmail)

        call.enqueue(object : Callback<ResponseBody> {

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                progressBar.hide()
                view!!.snackbar(context, context.getString(R.string.something_went_wrong), R.color.colorWarning)
            }

            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                progressBar.hide()
                context.toast("Resend code successful")
            }


        })

        return verificationResponse
    }


    fun removeForgotPassField(view: View, context: Context, progressBar: ProgressBar): LiveData<String> {
        val removeForgotPassField = MutableLiveData<String>()
        val api = RetrofitClient.apiService
        val call = api.removeForgotPassField(Flag.forgotPassEmail)

        call.enqueue(object : Callback<ResponseBody> {

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                progressBar.hide()
                view.snackbar(context, context.getString(R.string.something_went_wrong), R.color.colorWarning)
            }

            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                progressBar.hide()
                view.findNavController().navigate(R.id.forgotPasswordFragment)

                // context.toast("Resend code successful")

            }


        })

        return removeForgotPassField
    }


}
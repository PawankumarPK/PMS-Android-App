package com.purpleshade.pms.repository

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.ProgressBar
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.navigation.findNavController
import com.purpleshade.pms.R
import com.purpleshade.pms.model.SignUpModel
import com.purpleshade.pms.model.SignUpVerificationModel
import com.purpleshade.pms.model.VerificationModel
import com.purpleshade.pms.network.RetrofitClient
import com.purpleshade.pms.utils.customObject.Flag
import com.purpleshade.pms.utils.hide
import com.purpleshade.pms.utils.snackbar
import com.purpleshade.pms.utils.toast
import kotlinx.android.synthetic.main.verified_dialog.*
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by pawan on 27,March,2021
 */
class SignupVerificationRepository {
    var view: View? = null
    lateinit var mDialog: Dialog


    fun verifySignUp(context: Context,progressBar: ProgressBar): LiveData<String> {
        val verificationResponse = MutableLiveData<String>()
        val api = RetrofitClient.apiService
        val call = api.verifySignUp(Flag.userSignUpEmail, Flag.signUpToken)

        call.enqueue(object : Callback<ResponseBody> {

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                progressBar.hide()
                view!!.snackbar(context, context.getString(R.string.something_went_wrong), R.color.colorWarning)

            }

            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                progressBar.hide()
                successfullyVerifiedDialog(view!!,context)
               // view!!.snackbar(context, context.getString(R.string.register_successful), R.color.colorGreen)

            }


        })

        return verificationResponse
    }

    fun successfullyVerifiedDialog(view: View,context: Context) {
        mDialog = Dialog(context)
        val layout = LayoutInflater.from(context).inflate(R.layout.verified_dialog, null, false)
        mDialog.setContentView(layout)

        mDialog.mGoToLogin.setOnClickListener {
            mDialog.dismiss()
            view.findNavController().navigate(R.id.loginFragment)

        }
        mDialog.setCanceledOnTouchOutside(false)
        mDialog.show()
        mDialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

    }

    fun removeSignUp(context: Context,progressBar: ProgressBar): LiveData<String> {
        val removeSignUpResponse = MutableLiveData<String>()
        val api = RetrofitClient.apiService
        val call = api.removeSignUp(Flag.userSignUpEmail)

        call.enqueue(object : Callback<ResponseBody> {

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                progressBar.hide()
                view!!.snackbar(context, context.getString(R.string.something_went_wrong), R.color.colorWarning)
            }

            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                progressBar.hide()
                context.toast("please re-fill the form")
                // context.toast("Resend code successful")

            }

        })

        return removeSignUpResponse
    }


    fun getVerificationSignUpToken(context: Context, progressBar: ProgressBar): LiveData<String> {
        val verificationSignUpTokenResponse = MutableLiveData<String>()
        val api = RetrofitClient.apiService
        val call = api.verificationSignUpToken(Flag.userSignUpEmail)

        call.enqueue(object : Callback<SignUpVerificationModel> {

            override fun onFailure(call: Call<SignUpVerificationModel>, t: Throwable) {
                progressBar.hide()
                view!!.snackbar(context, context.getString(R.string.something_went_wrong), R.color.colorWarning)
            }

            override fun onResponse(call: Call<SignUpVerificationModel>, response: Response<SignUpVerificationModel>) {
                progressBar.hide()
                Flag.signUpToken  = response.body()!!.token.signUpToken.toString()

            }
        })

        return verificationSignUpTokenResponse
    }



}
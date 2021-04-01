package com.purpleshade.pms.repository

import android.content.Context
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.ProgressBar
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.navigation.findNavController
import com.purpleshade.pms.R
import com.purpleshade.pms.network.RetrofitClient
import com.purpleshade.pms.utils.customObject.Flag
import com.purpleshade.pms.utils.customObject.RoomRecordDetail
import com.purpleshade.pms.utils.gone
import com.purpleshade.pms.utils.hide
import com.purpleshade.pms.utils.snackbar
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by pawan on 28,March,2021
 */
class CreateNewPassRepository {

    var view: View? = null
    var confirmPin: String?=null


    fun createNewPassword(context: Context, editText: EditText, progressBar: ProgressBar): LiveData<String> {
        val verificationResponse = MutableLiveData<String>()
        val api = RetrofitClient.apiService
        val call = api.updatePassword(Flag.forgotPassEmail, editText.text.toString())

        call.enqueue(object : Callback<ResponseBody> {

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                progressBar.hide()
                view!!.snackbar(context, context.getString(R.string.something_went_wrong), R.color.colorWarning)
            }

            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                progressBar.hide()
                view!!.snackbar(context, context.getString(R.string.password_change_successfully), R.color.colorGreen)
                view!!.findNavController().navigate(R.id.loginFragment)

            }


        })

        return verificationResponse
    }


    fun updatePin(context: Context, progressBar: ProgressBar): LiveData<String> {
        val updateLockResponse = MutableLiveData<String>()
        val api = RetrofitClient.apiService
        val call = api.updateAppPin(Flag.forgotPassEmail,confirmPin!!)

        call.enqueue(object : Callback<ResponseBody> {

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                progressBar.gone()
                view!!.snackbar(context, context.getString(R.string.something_went_wrong), R.color.colorWarning)
            }

            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                progressBar.gone()
                view!!.snackbar(context, context.getString(R.string.pinCreated), R.color.colorGreen)
                view!!.findNavController().navigate(R.id.appPasswordFragment)

            }


        })

        return updateLockResponse
    }
}
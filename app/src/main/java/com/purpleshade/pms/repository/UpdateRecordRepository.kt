package com.purpleshade.pms.repository

import android.content.Context
import android.view.View
import android.widget.EditText
import android.widget.ProgressBar
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.navigation.findNavController
import com.purpleshade.pms.R
import com.purpleshade.pms.model.Records
import com.purpleshade.pms.model.SignUpModel
import com.purpleshade.pms.model.UpdateRecord
import com.purpleshade.pms.network.RetrofitClient
import com.purpleshade.pms.utils.customObject.RoomRecordDetail
import com.purpleshade.pms.utils.hide
import com.purpleshade.pms.utils.toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by pawan on 13,January,2021
 */
class UpdateRecordRepository {
    var title : EditText? = null
    var webAddress : EditText? = null
    var email : EditText? = null
    var password : EditText? = null
    var addNote : EditText? = null
    var view : View? = null

    fun getRecordDetails(context: Context, progressBar: ProgressBar): LiveData<String> {
        val responseGetRecordDetail = MutableLiveData<String>()
        val api = RetrofitClient.apiService
        val call = api.recordDetail(RoomRecordDetail.recordId)

        call.enqueue(object : Callback<Records> {
            override fun onFailure(call: Call<Records>, t: Throwable) {
                progressBar.hide()
                context.toast("Something went wrong")
            }

            override fun onResponse(call: Call<Records>, response: Response<Records>) {
                if (response.isSuccessful) {
                    progressBar.hide()
                    val msg = response.body()!!.message
                    val recordDetail = response.body()!!.recordDetail

                    for (i in recordDetail) {
                        title!!.setText(i.title)
                        webAddress!!.setText(i.websiteAddress)
                        email!!.setText(i.email)
                        password!!.setText(i.password)
                        addNote!!.setText(i.addNote)
                    }

                }
            }

        })
        return responseGetRecordDetail
    }

    fun updateRecord(context: Context, progressBar: ProgressBar, title: String, webAddress: String, email: String, password: String, addNote: String): LiveData<String> {
        val recordResponse = MutableLiveData<String>()
        val update = UpdateRecord(title, webAddress, email, password, addNote)

        val api = RetrofitClient.apiService
        val call = api.updateRecord(RoomRecordDetail.recordId, update)

        call.enqueue(object : Callback<SignUpModel> {
            override fun onFailure(call: Call<SignUpModel>, t: Throwable) {
                progressBar.hide()
                context.toast("Something went wrong")
            }

            override fun onResponse(call: Call<SignUpModel>, response: Response<SignUpModel>) {
                progressBar.hide()
                context.toast("Record Update Successfully")
                view!!.findNavController().navigate(R.id.action_updateRecordFragment_to_homeFragment)
            }

        })
        return recordResponse
    }

}
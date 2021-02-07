package com.purpleshade.pms.repository

import android.content.Context
import android.view.View
import android.widget.ProgressBar
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.findNavController
import com.purpleshade.pms.R
import com.purpleshade.pms.model.SignUpModel
import com.purpleshade.pms.network.RetrofitClient
import com.purpleshade.pms.utils.customObject.RoomRecordDetail
import com.purpleshade.pms.utils.hide
import com.purpleshade.pms.utils.snackbar
import com.purpleshade.pms.utils.toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by pawan on 17,January,2021
 */
class CreateRecordRepository : ViewModel() {

    fun fillRecordDetails(context: Context, view: View, progressBar: ProgressBar, title: String, webAddress: String, email: String, password: String, addNote: String,loginId:String): LiveData<String> {
        val responseAddData: MutableLiveData<String> = MutableLiveData()

        val api = RetrofitClient.apiService
        val call = api.addRecordDetail(title, webAddress, email, password, addNote,loginId)

        call.enqueue(object : Callback<SignUpModel> {
            override fun onFailure(call: Call<SignUpModel>, t: Throwable) {
                progressBar.hide()
                view.snackbar(context,"Oops! Something went wrong",R.color.colorWarning)
            }

            override fun onResponse(call: Call<SignUpModel>, response: Response<SignUpModel>) {
                progressBar.hide()
                RoomRecordDetail.roomDbEnable = "List Update"
                view.snackbar(context,"Add record successfully",R.color.colorGreen)
                view.findNavController().navigate(R.id.action_createRecordFragment_to_homeFragment)

            }

        })

        return responseAddData
    }
}
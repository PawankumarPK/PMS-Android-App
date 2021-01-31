package com.purpleshade.pms.repository

import android.content.Context
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.findNavController
import com.purpleshade.pms.R
import com.purpleshade.pms.activity.BaseActivity
import com.purpleshade.pms.db.RoomRecord
import com.purpleshade.pms.model.SignUpModel
import com.purpleshade.pms.network.RetrofitClient
import com.purpleshade.pms.utils.hide
import com.purpleshade.pms.utils.toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by pawan on 17,January,2021
 */
class CreateRecordRepository : ViewModel() {

    val roomRecord = RoomRecord()

    fun fillRecordDetails(context: Context, view: View, progressBar: ProgressBar, title: String, webAddress: String, email: String, password: String, addNote: String): LiveData<String> {
        val responseAddData: MutableLiveData<String> = MutableLiveData()

        val api = RetrofitClient.apiService
        val call = api.addRecordDetail(title, webAddress, email, password, addNote)

        call.enqueue(object : Callback<SignUpModel> {
            override fun onFailure(call: Call<SignUpModel>, t: Throwable) {
                progressBar.hide()
                context.toast("Something went wrong")
            }

            override fun onResponse(call: Call<SignUpModel>, response: Response<SignUpModel>) {
                progressBar.hide()

                /*val roomUser = BaseActivity.INSTANCE!!.myDao().user
                roomRecord.title = title
                roomRecord.email = email
                roomRecord.password = password
                roomRecord.addNote = addNote
                roomRecord.websiteAddress = webAddress
                roomRecord.loginId = roomUser.userId*/

                BaseActivity.INSTANCE!!.myDao().userRecords(roomRecord)

                context.toast("Add Record Successfully")

                view.findNavController().navigate(R.id.action_createRecordFragment_to_homeFragment)

            }

        })

        return responseAddData
    }
}
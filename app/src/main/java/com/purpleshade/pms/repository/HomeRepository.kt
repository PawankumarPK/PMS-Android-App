package com.purpleshade.pms.repository

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.purpleshade.pms.fragment.home.adapter.PasswordsAdapter
import com.purpleshade.pms.model.RecordList
import com.purpleshade.pms.model.Records
import com.purpleshade.pms.network.RetrofitClient
import com.purpleshade.pms.utils.JWTUtils
import com.purpleshade.pms.utils.toast
import kotlinx.android.synthetic.main.password_detail_bottomsheet.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by pawan on 17,January,2021
 */
class HomeRepository {

    var recordId = ""
    var title = ""
    var webAddress = ""
    var email = ""
    var password = ""
    var addNote = ""

    fun loadRecordList(context: Context, passwordList: ArrayList<RecordList>, adapter: PasswordsAdapter): LiveData<String> {
        val responseLoadRecordList: MutableLiveData<String> = MutableLiveData()
        val api = RetrofitClient.apiService
        val call = api.allRecords(JWTUtils.userId)

        call.enqueue(object : Callback<Records> {
            override fun onFailure(call: Call<Records>, t: Throwable) {
                context.toast("Something went wrong")
            }

            override fun onResponse(call: Call<Records>, response: Response<Records>) {
                context.toast("Record Load Successfully")
                if (response.isSuccessful) {
                    val record = response.body()!!.recordDetail
                    for (i in record) {
                        passwordList.add(i)
                        adapter.notifyDataSetChanged()
                    }
                }
            }

        })

        return responseLoadRecordList
    }


    fun getRecordDetails(id: String, context: Context, bottomSheetDialog: BottomSheetDialog) {
        val api = RetrofitClient.apiService
        val call = api.recordDetail(id)

        call.enqueue(object : Callback<Records> {
            override fun onFailure(call: Call<Records>, t: Throwable) {
                context.toast("Something went wrong")
            }

            override fun onResponse(call: Call<Records>, response: Response<Records>) {
                if (response.isSuccessful) {
                    context.toast("Get Record Successfully")
                    val msg = response.body()!!.message
                    val recordDetail = response.body()!!.recordDetail

                    for (i in recordDetail) {
                        title = i.title
                        webAddress = i.websiteAddress.toString()
                        email = i.email
                        password = i.password
                        addNote = i.addNote

                        bottomSheetDialog.mTitle.text = title
                        bottomSheetDialog.mWebAddress.text = webAddress
                        bottomSheetDialog.mEmail.text = email
                        bottomSheetDialog.mPassword.text = password
                        bottomSheetDialog.mAddNote.text = addNote

                    }

                }
            }

        })
    }

}
package com.purpleshade.pms.repository

import android.content.Context
import android.util.Log
import android.widget.ProgressBar
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.purpleshade.pms.activity.BaseActivity
import com.purpleshade.pms.db.User
import com.purpleshade.pms.fragment.home.adapter.PasswordsAdapter
import com.purpleshade.pms.model.RecordList
import com.purpleshade.pms.model.Records
import com.purpleshade.pms.model.SignUpModel
import com.purpleshade.pms.network.RetrofitClient
import com.purpleshade.pms.utils.customObject.RecordDetail
import com.purpleshade.pms.utils.hide
import com.purpleshade.pms.utils.toast
import kotlinx.android.synthetic.main.password_detail_bottomsheet.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by pawan on 17,January,2021
 */
class HomeRepository {

    var title = ""
    var webAddress = ""
    var email = ""
    var password = ""
    var addNote = ""

    fun loadRecordList(
        context: Context, passwordList: ArrayList<RecordList>, progressBar: ProgressBar,
        adapter: PasswordsAdapter, user: User
    ): LiveData<String> {
        val responseLoadRecordList: MutableLiveData<String> = MutableLiveData()
        val api = RetrofitClient.apiService
        val call = api.allRecords(RecordDetail.userId)

        call.enqueue(object : Callback<Records> {
            override fun onFailure(call: Call<Records>, t: Throwable) {
                context.toast("Something went wrong")
                progressBar.hide()
            }

            override fun onResponse(call: Call<Records>, response: Response<Records>) {
                context.toast("Record Load Successfully")
                if (response.isSuccessful) {
                    progressBar.hide()
                    val record = response.body()!!.recordDetail
                    for (i in record) {
                        passwordList.add(i).toString()
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

    fun deleteRecordItem(context: Context, id: String) {
        val api = RetrofitClient.apiService
        val call = api.deleteRecord(id)

        call.enqueue(object : Callback<SignUpModel> {
            override fun onFailure(call: Call<SignUpModel>, t: Throwable) {
                context.toast("Something went wrong")
            }

            override fun onResponse(call: Call<SignUpModel>, response: Response<SignUpModel>) {
                context.toast("Delete Record Successfully")
            }

        })
    }

}
package com.purpleshade.pms.repository

import android.content.Context
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.purpleshade.pms.R
import com.purpleshade.pms.activity.BaseActivity
import com.purpleshade.pms.db.RoomRecord
import com.purpleshade.pms.fragment.home.HomeViewModel
import com.purpleshade.pms.fragment.home.adapter.PasswordsAdapter
import com.purpleshade.pms.model.RecordList
import com.purpleshade.pms.model.Records
import com.purpleshade.pms.model.SignUpModel
import com.purpleshade.pms.network.RetrofitClient
import com.purpleshade.pms.utils.customObject.Flag
import com.purpleshade.pms.utils.customObject.RoomRecordDetail
import com.purpleshade.pms.utils.gone
import com.purpleshade.pms.utils.hide
import com.purpleshade.pms.utils.snackbar
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

    val roomRecord = RoomRecord()
    var recordList: List<RoomRecord> = ArrayList()

    var roomPasswordList: ArrayList<RoomRecord> = ArrayList()

    lateinit var homeViewModel: HomeViewModel


    fun loadRecordList(context: Context, textView: TextView, view: RecyclerView, passwordList: ArrayList<RecordList>, roomRecordList: ArrayList<RoomRecord>, progressBar: ProgressBar, adapter: PasswordsAdapter): LiveData<String> {
        val responseLoadRecordList: MutableLiveData<String> = MutableLiveData()
        val api = RetrofitClient.apiService
        val call = api.allRecords(RoomRecordDetail.userId)

        call.enqueue(object : Callback<Records> {
            override fun onFailure(call: Call<Records>, t: Throwable) {
                homeViewModel = HomeViewModel(context)

                Flag.somethingWentWrong = true

                homeViewModel.loadAdapter(view, context, roomPasswordList)
                val roomList = BaseActivity.INSTANCE!!.myDao().getUserRecords(RoomRecordDetail.userId) as ArrayList<RoomRecord>
                roomPasswordList.clear()
                roomPasswordList.addAll(roomList)

                context.toast(context.getString(R.string.something_went_wrong))
                progressBar.hide()

            }

            override fun onResponse(call: Call<Records>, response: Response<Records>) {
                homeViewModel = HomeViewModel(context)

                Flag.somethingWentWrong = false
                if (response.isSuccessful) {
                    progressBar.hide()
                    val recordDetail = response.body()!!.recordDetail
                    if (recordDetail.size > 0) {
                        textView.gone()
                    }
                    passwordList.clear()
                    for (i in recordDetail) {
                        roomRecord.recordId = i._id
                        roomRecord.title = i.title
                        roomRecord.websiteAddress = i.websiteAddress.toString()
                        roomRecord.email = i.email
                        roomRecord.password = i.password
                        roomRecord.addNote = i.addNote
                        roomRecord.loginId = RoomRecordDetail.userId

                        BaseActivity.INSTANCE!!.myDao().insertUserRecords(roomRecord)
                        passwordList.add(i).toString()

                    }

                    recordList = BaseActivity.INSTANCE!!.myDao().getUserRecords(RoomRecordDetail.userId)
                    roomRecordList.addAll(recordList)

/*
                    for (i in listDB.indices) {
                        val title = listDB[i].title

                    }*/
                    adapter.notifyDataSetChanged()
                    return

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
                bottomSheetDialog.mBottomSheetProgressBar.gone()
                context.toast(context.getString(R.string.something_went_wrong))
            }

            override fun onResponse(call: Call<Records>, response: Response<Records>) {
                if (response.isSuccessful) {
                    bottomSheetDialog.mBottomSheetProgressBar.gone()
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

    fun getRecordDetailsByRoom(id: String, bottomSheetDialog: BottomSheetDialog) {
        bottomSheetDialog.mBottomSheetProgressBar.gone()
        val recordList = BaseActivity.INSTANCE!!.myDao().loadSingle(id)
        for (i in recordList.indices) {
            bottomSheetDialog.mTitle.text = recordList[i].title
            bottomSheetDialog.mWebAddress.text = recordList[i].websiteAddress
            bottomSheetDialog.mEmail.text = recordList[i].email
            bottomSheetDialog.mPassword.text = recordList[i].password
            bottomSheetDialog.mAddNote.text = recordList[i].addNote
        }
    }

    fun deleteRecordItem(context: Context, view: View, id: String) {
        val api = RetrofitClient.apiService
        val call = api.deleteRecord(id)

        call.enqueue(object : Callback<SignUpModel> {
            override fun onFailure(call: Call<SignUpModel>, t: Throwable) {
                view.snackbar(context, context.getString(R.string.something_went_wrong), R.color.colorWarning)
            }

            override fun onResponse(call: Call<SignUpModel>, response: Response<SignUpModel>) {
                roomRecord.recordId = id
                BaseActivity.INSTANCE!!.myDao().deleteByRecordId(id)
                view.snackbar(context, context.getString(R.string.delete_record_successfully), R.color.colorGreen)
            }

        })
    }

}

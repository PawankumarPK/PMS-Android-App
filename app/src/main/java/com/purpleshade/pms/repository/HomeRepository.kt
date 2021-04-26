package com.purpleshade.pms.repository

import android.app.Activity
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.LinearLayoutManager
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
import com.purpleshade.pms.utils.*
import com.purpleshade.pms.utils.customObject.Flag
import com.purpleshade.pms.utils.customObject.RoomRecordDetail
import kotlinx.android.synthetic.main.password_detail_bottomsheet.*
import kotlinx.android.synthetic.main.password_detail_bottomsheet.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by pawan on 17,January,2021
 */
class HomeRepository : PasswordsAdapter.OnEventListener{
    var title = ""
    var webAddress = ""
    var email = ""
    var password = ""
    var addNote = ""
    var view: View? = null


    lateinit var bottomSheetDialog: BottomSheetDialog
    lateinit var context2: Context
    lateinit var activity2: Activity

    val roomRecord = RoomRecord()
    var recordList: List<RoomRecord> = ArrayList()

    var roomPasswordList: ArrayList<RoomRecord> = ArrayList()


    lateinit var adapter: PasswordsAdapter
    var passwordList: ArrayList<RecordList> = ArrayList()

    fun loadAdapter(view: RecyclerView, context: Context) {
        adapter = PasswordsAdapter(view, context, passwordList, roomPasswordList)
        adapter.onEventListener = this
        adapter.notifyDataSetChanged()
        view.adapter = adapter
        view.layoutManager = LinearLayoutManager(context)

    }

    fun loadRecordList(context: Context, actvity: Activity,textView: TextView, view: RecyclerView, passwordList: ArrayList<RecordList>, roomRecordList: ArrayList<RoomRecord>, progressBar: ProgressBar, adapter: PasswordsAdapter): LiveData<String> {
        val responseLoadRecordList: MutableLiveData<String> = MutableLiveData()
        val api = RetrofitClient.apiService
        val call = api.allRecords(RoomRecordDetail.userId)

        call.enqueue(object : Callback<Records> {
            override fun onFailure(call: Call<Records>, t: Throwable) {
                context2 = context
                activity2 = actvity
                Flag.somethingWentWrong = true
                loadAdapter(view, context)
                val roomList = BaseActivity.INSTANCE!!.myDao().getUserRecords(RoomRecordDetail.userId) as ArrayList<RoomRecord>
                roomPasswordList.clear()
                roomPasswordList.addAll(roomList)

                context.toast("Something went wrong")
                progressBar.hide()


            }

            override fun onResponse(call: Call<Records>, response: Response<Records>) {
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




    fun loadRecordListFromRoom(listDB: ArrayList<RoomRecord>, progressBar: ProgressBar): LiveData<String> {
        progressBar.hide()
        val responseLoadRecordList: MutableLiveData<String> = MutableLiveData()
        val recordList = BaseActivity.INSTANCE!!.myDao().getUserRecords(RoomRecordDetail.userId)
        listDB.addAll(recordList)
        for (i in listDB.indices) {
            val title = listDB[i].title
        }
        return responseLoadRecordList
    }


    fun getRecordDetails(id: String, context: Context, bottomSheetDialog: BottomSheetDialog) {
        val api = RetrofitClient.apiService
        val call = api.recordDetail(id)

        call.enqueue(object : Callback<Records> {
            override fun onFailure(call: Call<Records>, t: Throwable) {
                bottomSheetDialog.mBottomSheetProgressBar.gone()
                context.toast("Something went wrong")
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

    fun deleteRecordItem(context: Context, id: String) {
        val api = RetrofitClient.apiService
        val call = api.deleteRecord(id)

        call.enqueue(object : Callback<SignUpModel> {
            override fun onFailure(call: Call<SignUpModel>, t: Throwable) {
                view!!.snackbar(context, context.getString(R.string.something_went_wrong), R.color.colorWarning)
            }

            override fun onResponse(call: Call<SignUpModel>, response: Response<SignUpModel>) {
                roomRecord.recordId = id
                BaseActivity.INSTANCE!!.myDao().deleteByRecordId(id)
                view!!.snackbar(context, context.getString(R.string.delete_record_successfully), R.color.colorGreen)
//                view!!.snackbar(context,context.getString(R.string.delete_record_successfully), R.color.colorGreen)
            }

        })
    }

    private fun passwordDetailsBottomSheetVisible(actvity: Activity) {
        val bottomSheetView = LayoutInflater.from(context2).inflate(R.layout.password_detail_bottomsheet, actvity.findViewById<View>(R.id.bottomSheetContainer) as LinearLayout?)
        bottomSheetDialog.setContentView(bottomSheetView)

        bottomSheetView.mBottomSheetProgressBar.show()

        bottomSheetView.mClose.setOnClickListener {
            bottomSheetDialog.dismiss()
        }
        bottomSheetView.mWebAddress.setOnClickListener {
            context2.toast(context2.getString(R.string.internal_issue))
        }

    }

    override fun viewRecordDetails() {
    }


    override fun viewRecordDetailsUsingRoom() {
        bottomSheetDialog = BottomSheetDialog(context2)
        passwordDetailsBottomSheetVisible(activity2)
        getRecordDetailsByRoom(RoomRecordDetail.recordId, bottomSheetDialog)
        bottomSheetDialog.show()
    }

    override fun deleteRecord(id: String, pos: Int) {
        context2.toast("Something went wrong internet required")
    }

}

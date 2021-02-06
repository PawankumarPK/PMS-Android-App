package com.purpleshade.pms.fragment.home

import android.app.Activity
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import android.widget.ProgressBar
import androidx.lifecycle.ViewModel
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.purpleshade.pms.R
import com.purpleshade.pms.db.RoomRecord
import com.purpleshade.pms.db.RoomUser
import com.purpleshade.pms.fragment.home.adapter.PasswordsAdapter
import com.purpleshade.pms.fragment.home.adapter.RoomPasswordsAdapter
import com.purpleshade.pms.model.RecordList
import com.purpleshade.pms.repository.HomeRepository
import com.purpleshade.pms.utils.customInterface.AuthListener
import com.purpleshade.pms.utils.customObject.RoomRecordDetail
import com.purpleshade.pms.utils.customObject.ViewVisibility
import com.purpleshade.pms.utils.show
import kotlinx.android.synthetic.main.password_detail_bottomsheet.*
import kotlinx.android.synthetic.main.password_detail_bottomsheet.view.*


class HomeViewModel(val context: Context, val actvity: Activity, val repository: HomeRepository, val progressBar: ProgressBar, val user: RoomUser) : ViewModel(), PasswordsAdapter.OnEventListener, RoomPasswordsAdapter.RoomOnEventListener {

    lateinit var adapter: PasswordsAdapter
    lateinit var roomAdapter: RoomPasswordsAdapter
    var authListener: AuthListener? = null
    var passwordList: ArrayList<RecordList> = ArrayList()
    var roomPasswordList: ArrayList<RoomRecord> = ArrayList()

    lateinit var bottomSheetDialog: BottomSheetDialog

    //Insert list into recycleView
    fun loadAdapterList(view: RecyclerView) {
        progressBar.show()

        if (RoomRecordDetail.roomDbEnable || ViewVisibility.networkProblem) {
            roomDbLoadAdapter(view)
            val roomRepo = repository.loadRecordListFromRoom(roomPasswordList, progressBar)
            authListener!!.onSuccess(roomRepo)
        } else {
            loadAdapter(view)
            val repo = repository.loadRecordList(context, passwordList, progressBar, adapter)
            authListener!!.onSuccess(repo)
        }
    }

    fun fabButtonClick(view: View) {
        view.findNavController().navigate(R.id.action_homeFragment_to_createRecordFragment)
    }

    private fun loadAdapter(view: RecyclerView) {
        adapter = PasswordsAdapter(view, context, passwordList)
        adapter.notifyDataSetChanged()
        adapter.onEventListener = this
        view.adapter = adapter
        view.layoutManager = LinearLayoutManager(context)
        RoomRecordDetail.roomDbEnable = true

    }

    private fun roomDbLoadAdapter(view: RecyclerView) {
        roomAdapter = RoomPasswordsAdapter(view, context, roomPasswordList)
        roomAdapter.notifyDataSetChanged()
        roomAdapter.onEventListener = this
        view.adapter = roomAdapter
        view.layoutManager = LinearLayoutManager(context)

    }

    private fun bottomSheetVisible(actvity: Activity) {
        val bottomSheetView = LayoutInflater.from(context).inflate(R.layout.password_detail_bottomsheet, actvity.findViewById<View>(R.id.bottomSheetContainer) as LinearLayout?)
        bottomSheetDialog.setContentView(bottomSheetView)

        bottomSheetView.mClose.setOnClickListener {
            bottomSheetDialog.dismiss()
        }

    }

    override fun viewRecordDetails() {
        bottomSheetDialog = BottomSheetDialog(context)
        bottomSheetVisible(actvity)
        repository.getRecordDetails(RoomRecordDetail.recordId, context, bottomSheetDialog)
        bottomSheetDialog.show()
    }

    override fun viewRecordDetailsByRoom() {
        bottomSheetDialog = BottomSheetDialog(context)
        bottomSheetVisible(actvity)
        repository.getRecordDetailsByRoom(RoomRecordDetail.recordId, bottomSheetDialog)
        bottomSheetDialog.show()
    }

    override fun deleteRecord(id: String) {
        repository.deleteRecordItem(context, id)
    }


}
package com.purpleshade.pms.fragment.home

import android.app.Activity
import android.content.Context
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
import com.purpleshade.pms.db.RoomUser
import com.purpleshade.pms.fragment.home.adapter.PasswordsAdapter
import com.purpleshade.pms.model.RecordList
import com.purpleshade.pms.repository.HomeRepository
import com.purpleshade.pms.utils.customInterface.AuthListener
import com.purpleshade.pms.utils.customObject.RecordDetail
import com.purpleshade.pms.utils.show
import kotlinx.android.synthetic.main.password_detail_bottomsheet.view.*

class HomeViewModel(val context: Context, val actvity: Activity, val repository: HomeRepository, val progressBar: ProgressBar, val user: RoomUser) : ViewModel(), PasswordsAdapter.OnEventListener {

    lateinit var adapter: PasswordsAdapter
    var authListener: AuthListener? = null
    var passwordList: ArrayList<RecordList> = ArrayList()
    lateinit var bottomSheetDialog: BottomSheetDialog

    //Insert list into recycleView
    fun loadAdapterList(view: RecyclerView) {
        progressBar.show()
        loadAdapter(view)

        val repo = repository.loadRecordList(context, passwordList,progressBar,adapter, user)
        authListener!!.onSuccess(repo)
    }

    fun fabButtonClick(view:View){
        view.findNavController().navigate(R.id.action_homeFragment_to_createRecordFragment)
    }

    private fun loadAdapter(view: RecyclerView) {
        adapter = PasswordsAdapter(view,context, passwordList)
        adapter.notifyDataSetChanged()
        adapter.onEventListener = this
        view.adapter = adapter
        view.layoutManager = LinearLayoutManager(context)

    }

    private fun bottomSheetVisible(actvity: Activity) {
        val bottomSheetView = LayoutInflater.from(context).inflate(
            R.layout.password_detail_bottomsheet, actvity.findViewById<View>(R.id.bottomSheetContainer) as LinearLayout?
        )

        bottomSheetView.mClose.setOnClickListener {
            bottomSheetDialog.dismiss()
        }

        bottomSheetDialog.setContentView(bottomSheetView)
        bottomSheetDialog.show()
    }

    override fun viewRecordDetails() {
        bottomSheetDialog = BottomSheetDialog(context)
        repository.getRecordDetails(RecordDetail.recordId, context, bottomSheetDialog)
        bottomSheetVisible(actvity)
    }

    override fun deleteRecord(id: String) {
        repository.deleteRecordItem(context,id)

    }

}
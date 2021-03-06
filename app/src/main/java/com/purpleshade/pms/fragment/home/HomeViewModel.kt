package com.purpleshade.pms.fragment.home

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.ViewModel
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.purpleshade.pms.R
import com.purpleshade.pms.activity.BaseActivity
import com.purpleshade.pms.db.RoomRecord
import com.purpleshade.pms.db.RoomUser
import com.purpleshade.pms.fragment.home.adapter.PasswordsAdapter
import com.purpleshade.pms.model.RecordList
import com.purpleshade.pms.repository.HomeRepository
import com.purpleshade.pms.utils.customInterface.AuthListener
import com.purpleshade.pms.utils.customObject.Flag
import com.purpleshade.pms.utils.customObject.RoomRecordDetail
import com.purpleshade.pms.utils.gone
import com.purpleshade.pms.utils.show
import com.purpleshade.pms.utils.toast
import kotlinx.android.synthetic.main.delete_warning_dialog.view.*
import kotlinx.android.synthetic.main.password_detail_bottomsheet.view.*


class HomeViewModel(
    var context: Context,
    var view: View = View(context),
    private val blankPageMsg: TextView = TextView(context),
    private val actvity: Activity = Activity(),
    val repository: HomeRepository = HomeRepository(),
    val progressBar: ProgressBar = ProgressBar(context),
    val user: RoomUser = RoomUser()
) : ViewModel(), PasswordsAdapter.OnEventListener {


    lateinit var adapter: PasswordsAdapter
    var authListener: AuthListener? = null

    var passwordList: ArrayList<RecordList> = ArrayList()
    var roomPasswordList: ArrayList<RoomRecord> = ArrayList()

    lateinit var bottomSheetDialog: BottomSheetDialog
    lateinit var deleteBottomSheetDialog: BottomSheetDialog

    //Insert list into recycleView
    fun loadAdapterList(view: RecyclerView) {

        if (passwordList.size == 0)
            blankPageMsg.show()

        loadAdapter(view, context, roomPasswordList)

        if (Flag.networkProblem) {
            val roomList = BaseActivity.INSTANCE!!.myDao().getUserRecords(RoomRecordDetail.userId) as ArrayList<RoomRecord>
            roomPasswordList.clear()
            roomPasswordList.addAll(roomList)

            if (roomList.size == 0)
                blankPageMsg.show()
            else
                blankPageMsg.gone()

            /*for (i in roomList.indices) {
                val title = roomList[i].title
                Log.d("=====>>>",title.toString())
            }*/
            return
        } else if (!Flag.networkProblem && !Flag.somethingWentWrong) {
            progressBar.show()
            loadRecordList(view)
            return
        } else if (Flag.somethingWentWrong) {
            loadRecordList(view)
            return
        }

        // repository.view = view
    }

    private fun loadRecordList(view: RecyclerView) {
        val repo = repository.loadRecordList(context, blankPageMsg, view, passwordList, roomPasswordList, progressBar, adapter)
        authListener!!.onSuccess(repo)
        adapter.notifyDataSetChanged()

        val roomList = BaseActivity.INSTANCE!!.myDao().getUserRecords(RoomRecordDetail.userId) as ArrayList<RoomRecord>
        if (roomList.size == 0)
            blankPageMsg.show()
        else
            blankPageMsg.gone()

    }

    fun loadAdapter(view: RecyclerView, context: Context, roomPasswordList: ArrayList<RoomRecord>) {
        adapter = PasswordsAdapter(view, context, passwordList, roomPasswordList)
        adapter.notifyDataSetChanged()
        adapter.onEventListener = this
        view.adapter = adapter
        view.layoutManager = LinearLayoutManager(context)

    }

    private fun passwordDetailsBottomSheetVisible() {
        val bottomSheetView = LayoutInflater.from(context).inflate(R.layout.password_detail_bottomsheet, null)
        bottomSheetDialog.setContentView(bottomSheetView!!)

        bottomSheetView.mBottomSheetProgressBar.show()

        bottomSheetView.mClose.setOnClickListener {
            bottomSheetDialog.dismiss()
        }
        bottomSheetView.mWebAddress.setOnClickListener {
            openWebOnBrowser(bottomSheetView.mWebAddress)
        }

    }

    private fun openWebOnBrowser(textView: TextView) {
        var url = ""
        if (!url.startsWith("http://") && !url.startsWith("https://"))
            url = "http://" + textView.text.toString()

        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        startActivity(actvity, browserIntent, null)
    }

    private fun deleteBottomSheetVisible(id: String, pos: Int) {
        val deleteBottomSheetView = LayoutInflater.from(context).inflate(R.layout.delete_warning_dialog, null)
        deleteBottomSheetDialog.setContentView(deleteBottomSheetView)

        deleteBottomSheetView.mNo.setOnClickListener {
            deleteBottomSheetDialog.dismiss()
        }

        deleteBottomSheetView.mYes.setOnClickListener {

            if (Flag.somethingWentWrong) {
                context.toast(context.getString(R.string.internal_issue))
                deleteBottomSheetDialog.dismiss()
                return@setOnClickListener
            }

            passwordList.removeAt(pos)
            roomPasswordList.removeAt(pos)
            adapter.notifyItemRemoved(pos)
            adapter.notifyDataSetChanged()

            if (passwordList.size == 0) {
                blankPageMsg.show()
            }

            repository.deleteRecordItem(context, view, id)
            deleteBottomSheetDialog.dismiss()
        }

        deleteBottomSheetDialog.show()
    }

    override fun viewRecordDetails() {
        bottomSheetDialog = BottomSheetDialog(context)
        passwordDetailsBottomSheetVisible()
        repository.getRecordDetails(RoomRecordDetail.recordId, context, bottomSheetDialog)
        bottomSheetDialog.show()
    }

    override fun viewRecordDetailsUsingRoom() {
        bottomSheetDialog = BottomSheetDialog(context)
        passwordDetailsBottomSheetVisible()
        repository.getRecordDetailsByRoom(RoomRecordDetail.recordId, bottomSheetDialog)
        bottomSheetDialog.show()
    }

    override fun deleteRecord(id: String, pos: Int) {
        deleteBottomSheetDialog = BottomSheetDialog(context)
        deleteBottomSheetVisible(id, pos)
    }

    fun fabButtonClick(view: View) {
        view.findNavController().navigate(R.id.action_homeFragment_to_createRecordFragment)
    }

    fun profileImageClick(view: View) {
        view.findNavController().navigate(R.id.action_homeFragment_to_profileFragment)
    }
}
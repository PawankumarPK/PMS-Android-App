package com.purpleshade.pms.fragment.createRecord

import android.content.Context
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.room.Room
import com.purpleshade.pms.db.RoomRecord
import com.purpleshade.pms.db.RoomUser
import com.purpleshade.pms.repository.CreateRecordRepository
import com.purpleshade.pms.utils.customInterface.AuthListener
import com.purpleshade.pms.utils.customObject.RecordDetail
import com.purpleshade.pms.utils.customObject.UserResponse
import com.purpleshade.pms.utils.show
import com.purpleshade.pms.utils.toast

class CreateRecordViewModel(val context: Context, val repository: CreateRecordRepository, val progressBar: ProgressBar) : ViewModel() {
    var title: String? = null
    var webAddress = ""
    var email = ""
    var password = ""
    var addNote = ""

    var authListener: AuthListener? = null

    fun onAddButtonClick(view: View) {
        progressBar.show()
        if (title != null) {
            val repo = repository.fillRecordDetails(context, view, progressBar, title!!, webAddress, email, password, addNote,RecordDetail.userId)
            authListener!!.onSuccess(repo)

        } else {
            context.toast("Title is missing")
        }
    }

}
package com.purpleshade.pms.fragment.createRecord

import android.content.Context
import android.util.Log
import android.view.View
import android.widget.CheckBox
import android.widget.ProgressBar
import androidx.lifecycle.ViewModel
import com.google.android.material.textfield.TextInputLayout
import com.purpleshade.pms.R
import com.purpleshade.pms.db.RoomRecord
import com.purpleshade.pms.repository.CreateRecordRepository
import com.purpleshade.pms.utils.*
import com.purpleshade.pms.utils.customInterface.AuthListener
import com.purpleshade.pms.utils.customObject.Flag
import com.purpleshade.pms.utils.customObject.RoomRecordDetail

class CreateRecordViewModel(val context: Context, val repository: CreateRecordRepository, val progressBar: ProgressBar, private val checkBox: CheckBox, private val textInputLayout: TextInputLayout) : ViewModel() {
    var title: String? = null
    var webAddress = ""
    var email = ""
    var password = ""
    var addNote = ""
    var lockNotePin = ""

    var authListener: AuthListener? = null

    fun onAddButtonClick(view: View) {
        if (Flag.networkProblem){
            context.toast(context.getString(R.string.no_internet_connection))
            return
        }

        if (title != null) {
            progressBar.show()
            val repo = repository.fillRecordDetails(context, view, progressBar, title!!, webAddress, email, password, addNote, RoomRecordDetail.userId)
            authListener!!.onSuccess(repo)
        } else {
            view.snackbar(context, "Please enter a title for this record", R.color.colorBlackGrey)
            progressBar.hide()
        }
    }

    /*fun checkboxCheck(view: View) {
        if (checkBox.isChecked)
            textInputLayout.show()
        else
            textInputLayout.gone()

    }*/

}
package com.purpleshade.pms.fragment.createRecord

import android.content.Context
import android.view.View
import android.widget.CheckBox
import android.widget.ProgressBar
import androidx.lifecycle.ViewModel
import com.google.android.material.textfield.TextInputLayout
import com.purpleshade.pms.R
import com.purpleshade.pms.repository.CreateRecordRepository
import com.purpleshade.pms.utils.*
import com.purpleshade.pms.utils.customInterface.AuthListener
import com.purpleshade.pms.utils.customObject.RoomRecordDetail

class CreateRecordViewModel(val context: Context, val repository: CreateRecordRepository, val progressBar: ProgressBar, val checkBox: CheckBox, val textInputLayout: TextInputLayout) : ViewModel() {
    var title: String? = null
    var webAddress = ""
    var email = ""
    var password = ""
    var addNote = ""

    var authListener: AuthListener? = null

    fun onAddButtonClick(view: View) {
        progressBar.show()
        if (title != null) {
            val repo = repository.fillRecordDetails(context, view, progressBar, title!!, webAddress, email, password, addNote, RoomRecordDetail.userId)
            authListener!!.onSuccess(repo)

        } else {
            view.snackbar(context, "Please enter a title for this record", R.color.colorBlackGrey)
            progressBar.hide()
        }
    }

    fun checkboxCheck(view: View) {
        if (checkBox.isChecked)
            textInputLayout.show()
        else
            textInputLayout.gone()


    }

}
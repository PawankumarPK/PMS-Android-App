package com.purpleshade.pms.fragment.update

import android.content.Context
import android.view.View
import android.widget.EditText
import android.widget.ProgressBar
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.purpleshade.pms.R
import com.purpleshade.pms.repository.UpdateRecordRepository
import com.purpleshade.pms.utils.customInterface.AuthListener
import com.purpleshade.pms.utils.hide
import com.purpleshade.pms.utils.show
import com.purpleshade.pms.utils.snackbar


class UpdateRecordViewModel(val context: Context, val repository: UpdateRecordRepository, val progressBar: ProgressBar) : ViewModel() {

    var title: String? = null
    var webAddress: String? = null
    var email: String? = null
    var password: String? = null
    var addNote: String? = null

    var authListener: AuthListener? = null

    fun onUpdateButtonClick(view: View) {
        if (title.isNullOrEmpty()) {
            view.snackbar(context, "Please enter a title for this record", R.color.colorBlackGrey)
            progressBar.hide()
            return
        } else {
            progressBar.show()
            val repo = repository.updateRecord(context, progressBar, title!!, webAddress!!, email!!, password!!, addNote!!)
            authListener!!.onSuccess(repo)
            repository.view = view
        }

    }

    fun getRecordDetails(title: EditText, webAddress: EditText, email: EditText, password: EditText, addNote: EditText) {
        val repo = repository.getRecordDetails(context, progressBar)
        authListener!!.onSuccess(repo)
        repository.title = title
        repository.webAddress = webAddress
        repository.email = email
        repository.password = password
        repository.addNote = addNote

    }
}
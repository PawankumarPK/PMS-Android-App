package com.purpleshade.pms.fragment.createRecord

import android.content.Context
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.purpleshade.pms.repository.CreateRecordRepository
import com.purpleshade.pms.utils.customInterface.AuthListener

class CreateRecordViewModel(val context: Context, val repository: CreateRecordRepository) : ViewModel() {
    var title: String? = null
    var webAddress: String? = null
    var email: String? = null
    var password: String? = null
    var addNote: String? = null

    var progressBar : MutableLiveData<Boolean> = MutableLiveData()

     var authListener: AuthListener? = null

    fun onAddButtonClick(view: View) {
        progressBar.postValue(true)
        val repo = repository.fillRecordDetails(context,view,progressBar,title!!,webAddress!!,email!!,password!!,addNote!!)
        authListener!!.onSuccess(repo)
    }

}
package com.purpleshade.pms.fragment.appPassword

import android.util.Log
import android.view.View
import android.widget.EditText
import androidx.lifecycle.ViewModel
import androidx.navigation.findNavController
import com.purpleshade.pms.R
import com.purpleshade.pms.activity.BaseActivity
import com.purpleshade.pms.db.RoomUser
import com.purpleshade.pms.utils.customObject.RoomRecordDetail

class AppPasswordViewModel(val editText: EditText,val user : RoomUser) : ViewModel() {


    fun onDoneButtonClick(view: View){
        Log.d("---->>","===>>")
        val appPassword = editText.text.toString()
        if (appPassword == "12345") {
            view.findNavController().navigate(R.id.action_appPasswordFragment_to_homeFragment)

        }
    }

    fun loadLoginScreen(){
        val roomuserId = BaseActivity.INSTANCE!!.myDao().user
        RoomRecordDetail.userId = roomuserId.userId.toString()
    }

}
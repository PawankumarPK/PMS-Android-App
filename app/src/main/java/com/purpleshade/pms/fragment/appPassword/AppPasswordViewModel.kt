package com.purpleshade.pms.fragment.appPassword

import android.content.Context
import android.util.Log
import android.view.View
import android.widget.EditText
import androidx.lifecycle.ViewModel
import androidx.navigation.findNavController
import com.purpleshade.pms.R
import com.purpleshade.pms.activity.BaseActivity
import com.purpleshade.pms.db.RoomUser
import com.purpleshade.pms.utils.customObject.RoomRecordDetail
import com.purpleshade.pms.utils.snackbar
import com.purpleshade.pms.utils.toast

class AppPasswordViewModel(val context: Context, val editText: EditText, val user: RoomUser) : ViewModel() {


    fun onDoneButtonClick(view: View) {
        val listData = BaseActivity.INSTANCE!!.myDao().user
        val appPassword = editText.text.toString()
        if (appPassword != listData.appPassword) {
            context.toast("Incorrect PIN")
            return
        }
        view.findNavController().navigate(R.id.action_appPasswordFragment_to_homeFragment)
    }
}
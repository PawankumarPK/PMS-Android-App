package com.purpleshade.pms.fragment.profile

import android.content.Context
import android.view.View
import androidx.lifecycle.ViewModel
import com.purpleshade.pms.activity.BaseActivity
import com.purpleshade.pms.db.RoomUser
import com.purpleshade.pms.utils.customInterface.AuthListener

class ProfileViewModel(val context: Context, val view: View, val user: RoomUser) : ViewModel() {

    var username: String? = null
    var email: String? = null
   // var authListener: AuthListener? = null


    fun profileDetail() {
        val roomUser = BaseActivity.INSTANCE!!.myDao().user
        username = roomUser.username
        email = roomUser.email
    }

}
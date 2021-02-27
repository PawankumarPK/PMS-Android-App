package com.purpleshade.pms.fragment.profileSection.userProfile

import android.content.Context
import android.view.View
import androidx.lifecycle.ViewModel
import androidx.navigation.findNavController
import com.purpleshade.pms.R
import com.purpleshade.pms.activity.BaseActivity
import com.purpleshade.pms.db.RoomUser

class ProfileViewModel(val context: Context, val view: View, val user: RoomUser) : ViewModel() {

    var username: String? = null
    var email: String? = null
   // var authListener: AuthListener? = null


    fun profileDetail() {
        val roomUser = BaseActivity.INSTANCE!!.myDao().user
        username = roomUser.username
        email = roomUser.email
    }

    fun onEditButtonClick(){
        view.findNavController().navigate(R.id.action_profileFragment_to_editProfileFragment)
    }
}
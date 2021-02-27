package com.purpleshade.pms.fragment.profileSection.editProfile

import androidx.lifecycle.ViewModel
import com.purpleshade.pms.activity.BaseActivity

class EditProfileViewModel : ViewModel() {

    var username: String? = null
    var emergencyNumber: String? = null
    var email: String? = null


    fun getUserDetailForUpdate() {
        val roomUser = BaseActivity.INSTANCE!!.myDao().user
        username = roomUser.username
        email = roomUser.email
    }

}
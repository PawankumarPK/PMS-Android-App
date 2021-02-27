package com.purpleshade.pms.fragment.profileSection.editProfile

import android.content.Context
import android.view.View
import androidx.lifecycle.ViewModel
import com.purpleshade.pms.activity.BaseActivity
import com.purpleshade.pms.db.RoomUser
import com.purpleshade.pms.repository.EditProfileRepository
import com.purpleshade.pms.utils.customInterface.AuthListener

class EditProfileViewModel(val context: Context, val repository: EditProfileRepository) : ViewModel() {

    var username: String? = null
    var emergencyNumber: String? = null
    var email: String? = null
    var authListener: AuthListener? = null


    fun onUpdateProfileButtonClick(view: View) {
        val repo = repository.updateProfile(context, username!!, email!!)
        authListener!!.onSuccess(repo)

    }

    fun getUserDetailForUpdate() {
        val roomUser = BaseActivity.INSTANCE!!.myDao().user
        username = roomUser.username
        email = roomUser.email
    }

}
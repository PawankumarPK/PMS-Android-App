package com.purpleshade.pms.fragment.profileSection.userProfile

import android.content.Context
import android.view.View
import android.widget.TextView
import androidx.lifecycle.ViewModel
import androidx.navigation.findNavController
import com.purpleshade.pms.R
import com.purpleshade.pms.activity.BaseActivity
import com.purpleshade.pms.db.RoomUser
import com.purpleshade.pms.repository.ProfileRepository
import com.purpleshade.pms.utils.customInterface.AuthListener

class ProfileViewModel(val context: Context, val view: View, val user: RoomUser, private val usernameTextView: TextView, private val emailTextView: TextView,private val nicknameTextView: TextView ,val repository: ProfileRepository) : ViewModel() {

    var username: String? = null
    var email: String? = null
    var authListener: AuthListener? = null


    fun profileDetail() {
        val repo = repository.profileDetails(context, usernameTextView, emailTextView,nicknameTextView)
        authListener!!.onSuccess(repo)

        /*val roomUser = BaseActivity.INSTANCE!!.myDao().user
        username = roomUser.username
        email = roomUser.email*/
    }

    fun onEditButtonClick() {
        view.findNavController().navigate(R.id.action_profileFragment_to_editProfileFragment)
    }
}
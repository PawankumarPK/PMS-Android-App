package com.purpleshade.pms.fragment.profileSection.userProfile

import android.content.Context
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.lifecycle.ViewModel
import androidx.navigation.findNavController
import com.purpleshade.pms.R
import com.purpleshade.pms.activity.BaseActivity
import com.purpleshade.pms.db.RoomUser
import com.purpleshade.pms.repository.ProfileRepository
import com.purpleshade.pms.utils.customInterface.AuthListener
import com.purpleshade.pms.utils.customObject.Flag
import com.purpleshade.pms.utils.show
import com.purpleshade.pms.utils.toast

class ProfileViewModel(val context: Context, val view: View, val progressBar: ProgressBar, val user: RoomUser, private val usernameTextView: TextView, private val emailTextView: TextView, private val nicknameTextView: TextView, val repository: ProfileRepository) : ViewModel() {

    var username: String? = null
    var email: String? = null
    var screenLock: String? = null
    var authListener: AuthListener? = null
    val lockStatus = BaseActivity.INSTANCE!!.myDao().user

    fun profileDetail() {
        if (!Flag.networkProblem) {
            progressBar.show()
            val repo = repository.profileDetails(context, view, progressBar, usernameTextView, emailTextView, nicknameTextView)
            authListener!!.onSuccess(repo)
        } else {
            profileDetailUsingRoomDb()
        }

        if (lockStatus.lockAppStatus == "on") {
            screenLock = "Disable Screen Lock"
        } else {
            lockStatus.lockAppStatus = "off"
            BaseActivity.INSTANCE!!.myDao().userDetails(lockStatus)
            Log.d("---->>",lockStatus.lockAppStatus.toString())
            screenLock = "Enable Screen Lock"
        }

    }

    private fun profileDetailUsingRoomDb() {
        username = user.username
        email = user.email
    }

    fun onEditButtonClick(view: View) {
        if (!Flag.networkProblem) {
            Log.d("---->>", lockStatus.lockAppStatus.toString())
            view.findNavController().navigate(R.id.action_profileFragment_to_editProfileFragment)
        }
        else
            context.toast("Could not connect to internet")
    }

    fun onEnableScreenLockClick(view: View) {
        view.findNavController().navigate(R.id.action_profileFragment_to_enablePasswordFragment)
    }

}
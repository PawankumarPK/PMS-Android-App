package com.purpleshade.pms.fragment.profileSection.userProfile

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.Uri
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.content.ContextCompat
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
import kotlinx.android.synthetic.main.logout_msg_dialog.*


class ProfileViewModel(val context: Context, val view: View, val progressBar: ProgressBar, val user: RoomUser, private val usernameTextView: TextView, private val emailTextView: TextView, private val nicknameTextView: TextView, val repository: ProfileRepository, val actvity: Activity) : ViewModel() {

    var username: String? = null
    var email: String? = null
    var screenLock: String? = null
    var authListener: AuthListener? = null
    val lockStatus = BaseActivity.INSTANCE!!.myDao().user
    lateinit var mDialog: Dialog

    fun profileDetail() {
        if (!Flag.networkProblem) {
            progressBar.show()
            val repo = repository.profileDetails(context, view, progressBar, usernameTextView, emailTextView, nicknameTextView)
            authListener!!.onSuccess(repo)
        } else {
            profileDetailUsingRoomDb()
        }

        if (lockStatus.lockAppStatus == "on") {
            screenLock = context.getString(R.string.disable_screen_lock)
        } else {
            lockStatus.lockAppStatus = "off"
            BaseActivity.INSTANCE!!.myDao().userDetails(lockStatus)
            screenLock = context.getString(R.string.enable_screen_lock)
        }

    }
    
    fun openWebOnBrowser(view: View) {
        var url = ""
        if (!url.startsWith("http://") && !url.startsWith("https://"))
            url = "http://" + "www.privacypolicies.com/live/38c499a8-cbb8-40ff-a761-9669d10b7358"

        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        ContextCompat.startActivity(actvity, browserIntent, null)
    }


    private fun profileDetailUsingRoomDb() {
        username = user.username
        email = user.email
    }

    fun logoutDialog(view: View) {
        if (Flag.networkProblem) {
            context.toast(context.getString(R.string.no_internet_connection))
            return
        }

        mDialog = Dialog(context)
        val layout = LayoutInflater.from(context).inflate(R.layout.logout_msg_dialog, null, false)
        mDialog.setContentView(layout)

        mDialog.mYes.setOnClickListener {
            logoutOnClick(view)
            mDialog.dismiss()
        }
        mDialog.mNo.setOnClickListener {
            mDialog.dismiss()
        }
        mDialog.show()

    }

    private fun logoutOnClick(view: View) {
        if (Flag.networkProblem) {
            context.toast(context.getString(R.string.no_internet_connection))
            return
        }
        progressBar.show()
        val repo = repository.logout(context, view, progressBar)
        authListener!!.onSuccess(repo)
    }


    fun onEditButtonClick(view: View) {
        if (!Flag.networkProblem) {
            view.findNavController().navigate(R.id.action_profileFragment_to_editProfileFragment)
        } else
            context.toast(context.getString(R.string.no_internet_connection))
    }

    fun onEnableScreenLockClick(view: View) {
        view.findNavController().navigate(R.id.action_profileFragment_to_enablePasswordFragment)
    }

}
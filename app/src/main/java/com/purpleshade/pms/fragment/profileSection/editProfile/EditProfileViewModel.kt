package com.purpleshade.pms.fragment.profileSection.editProfile

import android.content.Context
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.lifecycle.ViewModel
import androidx.navigation.findNavController
import com.purpleshade.pms.R
import com.purpleshade.pms.db.RoomUser
import com.purpleshade.pms.repository.EditProfileRepository
import com.purpleshade.pms.utils.customInterface.AuthListener
import com.purpleshade.pms.utils.customObject.Flag
import com.purpleshade.pms.utils.show

class EditProfileViewModel(val context: Context, val view: View, val progressBar: ProgressBar, val usernameTextView: TextView, val emailTextView: TextView, val repository: EditProfileRepository) : ViewModel() {

    var username: String? = null
    var email: String? = null
    var authListener: AuthListener? = null


    fun onUpdateProfileButtonClick(view: View) {
        progressBar.show()
        val repo = repository.updateProfile(context, view, progressBar, username!!, email!!)
        authListener!!.onSuccess(repo)

    }

    fun getUserDetailForUpdate() {
        progressBar.show()
        val repo = repository.getProfileDetailForUpdate(context, view, progressBar, usernameTextView, emailTextView)
        authListener!!.onSuccess(repo)
    }

}
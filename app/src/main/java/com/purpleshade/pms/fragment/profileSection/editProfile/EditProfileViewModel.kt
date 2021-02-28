package com.purpleshade.pms.fragment.profileSection.editProfile

import android.content.Context
import android.view.View
import android.widget.TextView
import androidx.lifecycle.ViewModel
import androidx.navigation.findNavController
import com.purpleshade.pms.R
import com.purpleshade.pms.repository.EditProfileRepository
import com.purpleshade.pms.utils.customInterface.AuthListener
import com.purpleshade.pms.utils.customObject.Flag

class EditProfileViewModel(val context: Context, val view: View, val usernameTextView: TextView, val emailTextView: TextView, val repository: EditProfileRepository) : ViewModel() {

    var username: String? = null
    var email: String? = null
    var authListener: AuthListener? = null


    fun onUpdateProfileButtonClick(view: View) {
        val repo = repository.updateProfile(context,view, username!!, email!!)
        authListener!!.onSuccess(repo)
        Flag.profileDetails = "Data"
    }

    fun getUserDetailForUpdate() {
        val repo = repository.getProfileDetailForUpdate(context,view, usernameTextView, emailTextView)
        authListener!!.onSuccess(repo)

        /*val roomUser = BaseActivity.INSTANCE!!.myDao().user
        username = roomUser.username
        email = roomUser.email*/
    }

}
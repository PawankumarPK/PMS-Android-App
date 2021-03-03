package com.purpleshade.pms.fragment.profileSection.userProfile

import android.content.Context
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.purpleshade.pms.db.RoomUser
import com.purpleshade.pms.repository.ProfileRepository

/**
 * Created by pawan on 27,February,2021
 */
@Suppress("UNCHECKED_CAST")
class ProfileViewModelFactory(val context: Context, val view: View, val progressBar: ProgressBar, val user: RoomUser, val username: TextView, val email: TextView, val nickname: TextView, val repository: ProfileRepository) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ProfileViewModel(context, view, progressBar, user, username, email, nickname, repository) as T
    }
}
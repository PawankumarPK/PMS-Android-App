package com.purpleshade.pms.fragment.profileSection.editProfile

import android.content.Context
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.purpleshade.pms.db.RoomUser
import com.purpleshade.pms.repository.EditProfileRepository

/**
 * Created by pawan on 28,February,2021
 */
@Suppress("UNCHECKED_CAST")
class EditProfileViewModelFactory(val context: Context, val progressBar: ProgressBar, val view: View, val username: TextView, val email: TextView, val repository: EditProfileRepository) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return EditProfileViewModel(context, view, progressBar, username, email, repository) as T
    }
}
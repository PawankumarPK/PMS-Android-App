package com.purpleshade.pms.fragment.profile

import android.content.Context
import android.view.View
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.purpleshade.pms.db.RoomUser

/**
 * Created by pawan on 27,February,2021
 */
@Suppress("UNCHECKED_CAST")
class ProfileViewModelFactory(val context: Context, val view: View, val user: RoomUser) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ProfileViewModel(context, view, user) as T
    }
}
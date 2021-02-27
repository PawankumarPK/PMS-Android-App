package com.purpleshade.pms.fragment.profileSection.editProfile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

/**
 * Created by pawan on 28,February,2021
 */
@Suppress("UNCHECKED_CAST")
class EditProfileViewModelFactory :ViewModelProvider.NewInstanceFactory(){

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return EditProfileViewModel() as T
    }
}
package com.purpleshade.pms.fragment.login.forgotPassword

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

/**
 * Created by pawan on 28,March,2021
 */
@Suppress("UNCHECKED_CAST")
class ForgotPasswordViewModelFactory():ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ForgotPasswordViewModel() as T
    }
}
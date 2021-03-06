package com.purpleshade.pms.fragment.login.forgotPassword

import android.content.Context
import android.widget.ProgressBar
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.purpleshade.pms.repository.ForgotPasswordRepository

/**
 * Created by pawan on 28,March,2021
 */
@Suppress("UNCHECKED_CAST")
class ForgotPasswordViewModelFactory(val context: Context, val progressBar:ProgressBar,val repository: ForgotPasswordRepository) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ForgotPasswordViewModel(context, progressBar,repository) as T
    }
}
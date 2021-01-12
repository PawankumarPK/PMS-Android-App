package com.purpleshade.pms.fragment.signup

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.purpleshade.pms.repository.LoginRepository
import com.purpleshade.pms.repository.SignupRepository

/**
 * Created by pawan on 04,June,2020
 */
@Suppress("UNCHECKED_CAST")
class SignUpViewModelFactory(private val context: Context, private val repository: SignupRepository) :
    ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return SignUpViewModel(context,repository) as T
    }
}
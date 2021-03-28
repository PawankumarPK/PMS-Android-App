package com.purpleshade.pms.fragment.login.forgotPassword.createNewPassword

import android.content.Context
import android.widget.EditText
import android.widget.ImageView
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.purpleshade.pms.fragment.login.forgotPassword.ForgotPasswordViewModel
import com.purpleshade.pms.repository.CreateNewPassRepository
import com.purpleshade.pms.repository.ForgotPasswordRepository

@Suppress("UNCHECKED_CAST")
class CreateNewPassViewModelFactory(val context: Context, val editText: EditText,val imageView: ImageView,val repository: CreateNewPassRepository) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return CreateNewPassViewModel(context, editText,imageView,repository) as T
    }
}
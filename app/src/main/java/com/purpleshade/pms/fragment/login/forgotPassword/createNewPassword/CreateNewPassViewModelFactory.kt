package com.purpleshade.pms.fragment.login.forgotPassword.createNewPassword

import android.content.Context
import android.widget.EditText
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.purpleshade.pms.repository.CreateNewPassRepository

@Suppress("UNCHECKED_CAST")
class CreateNewPassViewModelFactory(val context: Context, val editText: EditText, val imageView: ImageView, val progressBar: ProgressBar, val repository: CreateNewPassRepository) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return CreateNewPassViewModel(context, editText, imageView, progressBar, repository) as T
    }
}
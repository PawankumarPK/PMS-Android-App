package com.purpleshade.pms.fragment.signup

import android.content.Context
import android.widget.EditText
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.purpleshade.pms.repository.LoginRepository
import com.purpleshade.pms.repository.SignupRepository

/**
 * Created by pawan on 04,June,2020
 */
@Suppress("UNCHECKED_CAST")
class SignUpViewModelFactory(private val context: Context, private val repository: SignupRepository,val progressBar: ProgressBar,val imageView: ImageView,val editText: EditText) :
    ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return SignUpViewModel(context,repository,progressBar,imageView,editText) as T
    }
}
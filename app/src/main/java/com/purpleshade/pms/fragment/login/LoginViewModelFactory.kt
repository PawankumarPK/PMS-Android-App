package com.purpleshade.pms.fragment.login

import android.content.Context
import android.widget.EditText
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.purpleshade.pms.db.RoomUser
import com.purpleshade.pms.repository.LoginRepository

/**
 * Created by pawan on 04,June,2020
 */
@Suppress("UNCHECKED_CAST")
class LoginViewModelFactory(private val context: Context, private val repository: LoginRepository, val progress: ProgressBar, val user: RoomUser, val imageView: ImageView, val editText: EditText) :
    ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return LoginViewModel(context, repository, progress, user,imageView,editText) as T
    }
}
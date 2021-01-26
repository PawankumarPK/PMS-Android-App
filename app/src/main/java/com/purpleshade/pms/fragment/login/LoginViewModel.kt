package com.purpleshade.pms.fragment.login

import android.content.Context
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.findNavController
import com.purpleshade.pms.R
import com.purpleshade.pms.repository.LoginRepository
import com.purpleshade.pms.utils.customInterface.AuthListener
import com.purpleshade.pms.utils.hide
import com.purpleshade.pms.utils.show
import com.purpleshade.pms.utils.toast

class LoginViewModel(val context: Context, private val repository: LoginRepository, val progress: ProgressBar) : ViewModel() {

    var email: String? = null
    var password: String? = null
    var authListener: AuthListener? = null

    val progressBar: MutableLiveData<Boolean> = MutableLiveData()

    fun onLoginButtonClick(view: View) {
        progress.show()
        if (email.isNullOrEmpty() || password.isNullOrEmpty()) {
            progress.hide()
            context.toast("Field Empty")
            return
        }

        val repo = repository.doLogin(email.toString(), password.toString(), progressBar, context, view, progress)
        authListener!!.onSuccess(repo)
    }

    fun onRegisterClick(view: View) {
        view.findNavController().navigate(R.id.action_loginFragment_to_signUpFragment)

    }
}
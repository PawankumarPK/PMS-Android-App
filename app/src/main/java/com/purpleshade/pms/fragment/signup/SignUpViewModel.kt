package com.purpleshade.pms.fragment.signup

import android.content.Context
import android.view.View
import android.widget.ProgressBar
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.findNavController
import com.purpleshade.pms.R
import com.purpleshade.pms.repository.SignupRepository
import com.purpleshade.pms.utils.customInterface.AuthListener
import com.purpleshade.pms.utils.hide
import com.purpleshade.pms.utils.show
import com.purpleshade.pms.utils.toast

class SignUpViewModel(val context: Context, private val repository: SignupRepository,val progressBar: ProgressBar) : ViewModel() {

    var username: String? = null
    var email: String? = null
    var password: String? = null
    var confirmPassword: String? = null

    var authListener: AuthListener? = null


    fun signUpButtonClick(view: View) {
        progressBar.show()
        if (username.isNullOrEmpty() || email.isNullOrEmpty() || password.isNullOrEmpty() || confirmPassword.isNullOrEmpty()) {
            progressBar.hide()
            context.toast("Field Empty")
            return
        }

        val repo = repository.signUp(username!!, email!!, password!!, confirmPassword!!, context, progressBar)
        authListener!!.onSuccess(repo)
        repository.view = view

    }

    fun onLoginClick(view: View) {
        view.findNavController().navigate(R.id.action_signUpFragment_to_loginFragment)

    }
}
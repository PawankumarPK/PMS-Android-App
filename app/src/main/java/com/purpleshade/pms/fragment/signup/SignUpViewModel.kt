package com.purpleshade.pms.fragment.signup

import android.content.Context
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.findNavController
import com.purpleshade.pms.R
import com.purpleshade.pms.repository.SignupRepository
import com.purpleshade.pms.utils.customInterface.AuthListener
import com.purpleshade.pms.utils.toast

class SignUpViewModel(val context: Context, private val repository: SignupRepository) : ViewModel() {

    var username: String? = null
    var email: String? = null
    var password: String? = null
    var confirmPassword: String? = null

    var authListener: AuthListener? = null
    var progressBar: MutableLiveData<Boolean> = MutableLiveData()


    fun signUpButtonClick(view: View) {
        progressBar.postValue(true)
        if (username.isNullOrEmpty() || email.isNullOrEmpty() || password.isNullOrEmpty() || confirmPassword.isNullOrEmpty()) {
            progressBar.postValue(false)
            context.toast("Field Empty")
            return
        }

        val repository = repository.signUp(username!!, email!!, password!!, confirmPassword!!, context, progressBar)
        authListener!!.onSuccess(repository)

    }

    fun onLoginClick(view: View) {
        view.findNavController().navigate(R.id.action_signUpFragment_to_loginFragment)

    }
}
package com.purpleshade.pms.fragment.login

import android.content.Context
import android.view.View
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.findNavController
import com.purpleshade.pms.R
import com.purpleshade.pms.repository.LoginRepository
import com.purpleshade.pms.utils.customInterface.AuthListener

class LoginViewModel(val context: Context, private val repository: LoginRepository) : ViewModel() {

    var email: String? = null
    var password: String? = null
    var authListener: AuthListener? = null

    val progressBar: MutableLiveData<Boolean> = MutableLiveData()
    val network : NetworkConnectionIntercepter? = null

    fun onLoginButtonClick(view: View) {
        progressBar.postValue(true)
        if (email.isNullOrEmpty() || password.isNullOrEmpty()) {
            progressBar.postValue(false)
            Toast.makeText(context, "Field Empty", Toast.LENGTH_SHORT).show()
            return
        }

        val repo = repository.doLogin(email.toString(), password.toString(), progressBar, context, view)
        authListener!!.onSuccess(repo)
    }

    fun onRegisterClick(view : View){
        view.findNavController().navigate(R.id.action_loginFragment_to_signUpFragment)

    }
}
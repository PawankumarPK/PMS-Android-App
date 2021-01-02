package com.purpleshade.pms.fragment.login

import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModel
import androidx.navigation.findNavController
import com.purpleshade.pms.R
import com.purpleshade.pms.repository.LoginRepository
import com.purpleshade.pms.utils.customInterface.AuthListener
import com.purpleshade.pms.utils.customObject.UserResponse

class LoginViewModel : ViewModel() {

    var email: String? = null
    var password: String? = null
    var authListener: AuthListener? = null


    fun onLoginButtonClick(view: View) {
        authListener?.onStarted()
        if (email == null || password == null) {
            authListener!!.onFailed("Field Empty")
            return
        }

        val repository = LoginRepository().doLogin(email.toString(),password.toString())
        authListener!!.onSuccess(repository)

        /*val repository = email?.let { password?.let { it1 -> LoginRepository().doLogin(it, it1) } }
        repository?.let { authListener?.onSuccess(it) }
*/

        /*if (UserResponse.response.equals("User Found"))
            view.findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
        else
            authListener!!.onFailed("InValid Credential")*/

    }
}
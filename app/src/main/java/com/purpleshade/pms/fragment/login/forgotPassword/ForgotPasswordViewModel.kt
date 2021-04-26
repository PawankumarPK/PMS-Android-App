package com.purpleshade.pms.fragment.login.forgotPassword

import android.content.Context
import android.view.View
import android.widget.ProgressBar
import androidx.lifecycle.ViewModel
import androidx.navigation.findNavController
import com.purpleshade.pms.R
import com.purpleshade.pms.repository.ForgotPasswordRepository
import com.purpleshade.pms.utils.customInterface.AuthListener
import com.purpleshade.pms.utils.customObject.Flag
import com.purpleshade.pms.utils.hide
import com.purpleshade.pms.utils.show
import com.purpleshade.pms.utils.snackbar
import com.purpleshade.pms.utils.toast

class ForgotPasswordViewModel(val context: Context, val progressBar: ProgressBar, val repository: ForgotPasswordRepository) : ViewModel() {

    var label: String? = null
    var email: String? = null
    var authListener: AuthListener? = null
    var regex: Regex? = null
    var emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"

    fun sendInstructionOnClick(view: View) {
        if (Flag.networkProblem) {
            context.toast(context.getString(R.string.no_internet_connection))
            return
        }
        regex = Regex(emailPattern)
        when {
            email.isNullOrEmpty() -> {
                view.snackbar(context, "Email is required", R.color.colorBlackGrey)
                return
            }
            !email!!.matches(regex!!) -> {
                view.snackbar(context, "Address should be example@gmail.com", R.color.colorBlackGrey)
                return
            }
        }

        Flag.forgotPassEmail = email!!
        progressBar.show()
        val repo = repository.sendEmail(context, progressBar)
        authListener!!.onSuccess(repo)
        repository.view = view

    }

    fun backOnClick(view:View){
        view.findNavController().navigate(R.id.loginFragment)
    }

    fun appPinEvent() {
        if (Flag.appPin)
            label = "Forgot Pin"
        else
            label = "Forgot Password"

    }


}
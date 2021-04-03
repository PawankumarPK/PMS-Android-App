package com.purpleshade.pms.fragment.login.forgotPassword

import android.content.Context
import android.view.View
import android.widget.ProgressBar
import androidx.lifecycle.ViewModel
import com.purpleshade.pms.R
import com.purpleshade.pms.repository.ForgotPasswordRepository
import com.purpleshade.pms.utils.customInterface.AuthListener
import com.purpleshade.pms.utils.customObject.Flag
import com.purpleshade.pms.utils.hide
import com.purpleshade.pms.utils.show
import com.purpleshade.pms.utils.snackbar

class ForgotPasswordViewModel(val context: Context, val progressBar: ProgressBar, val repository: ForgotPasswordRepository) : ViewModel() {

    var label: String? = null
    var email: String? = null
    var authListener: AuthListener? = null
    var regex: Regex? = null
    var emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"

    fun sendInstructionOnClick(view: View) {
        progressBar.show()
        regex = Regex(emailPattern)
        when {
            email.isNullOrEmpty() -> {
                view.snackbar(context, "Email is required", R.color.colorBlackGrey)
                progressBar.hide()
                return
            }
            !email!!.matches(regex!!) -> {
                view.snackbar(context, "Address should be example@gmail.com", R.color.colorBlackGrey)
                progressBar.hide()
                return
            }
        }

        Flag.forgotPassEmail = email!!
        val repo = repository.sendEmail(context, progressBar)
        authListener!!.onSuccess(repo)

        val getVerificationTokenRepo = repository.verificationToken(context,progressBar)
        authListener!!.onSuccess(getVerificationTokenRepo)

        repository.view = view

    }

    fun appPinEvent() {
        if (Flag.appPin)
            label = "Forgot Pin"
        else
            label = "Forgot Password"

    }


}
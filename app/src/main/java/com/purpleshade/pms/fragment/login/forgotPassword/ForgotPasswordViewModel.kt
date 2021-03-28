package com.purpleshade.pms.fragment.login.forgotPassword

import android.content.Context
import android.view.View
import androidx.lifecycle.ViewModel
import com.purpleshade.pms.repository.ForgotPasswordRepository
import com.purpleshade.pms.utils.customInterface.AuthListener
import com.purpleshade.pms.utils.customObject.Flag

class ForgotPasswordViewModel(val context: Context, val repository: ForgotPasswordRepository) : ViewModel() {

    var email: String? = null
    var authListener: AuthListener? = null

    fun sendInstructionOnClick(view: View) {
        Flag.forgotPassEmail = email!!
        val repo = repository.sendEmail(context)
        authListener!!.onSuccess(repo)

        repository.view = view

    }


}
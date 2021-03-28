package com.purpleshade.pms.fragment.login.forgotPassword.createNewPassword

import android.content.Context
import android.view.View
import android.widget.EditText
import androidx.lifecycle.ViewModel
import com.purpleshade.pms.R
import com.purpleshade.pms.repository.CreateNewPassRepository
import com.purpleshade.pms.utils.customInterface.AuthListener
import com.purpleshade.pms.utils.customObject.Flag
import com.purpleshade.pms.utils.snackbar

class CreateNewPassViewModel(val context: Context, val editText: EditText,val repository: CreateNewPassRepository) : ViewModel() {

    var password: String? = null
    var confirmPassword: String? = null
    var authListener: AuthListener? = null


    fun resetButtonOnClick(view: View) {

        when {
            password.isNullOrEmpty() -> {
                view.snackbar(context, "Please enter a password", R.color.colorBlackGrey)
                //progressBar.hide()
                return
            }
            confirmPassword.isNullOrEmpty() || confirmPassword != password -> {
                view.snackbar(context, "Password did not match", R.color.colorBlackGrey)
                // progressBar.hide()
                return
            }
        }

        val repo = repository.createNewPassword(context,editText)
        authListener!!.onSuccess(repo)

        repository.view = view

    }

}
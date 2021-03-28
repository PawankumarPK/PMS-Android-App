package com.purpleshade.pms.fragment.login.forgotPassword.createNewPassword

import android.content.Context
import android.text.method.PasswordTransformationMethod
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.lifecycle.ViewModel
import com.purpleshade.pms.R
import com.purpleshade.pms.repository.CreateNewPassRepository
import com.purpleshade.pms.utils.customInterface.AuthListener
import com.purpleshade.pms.utils.customObject.Flag
import com.purpleshade.pms.utils.hide
import com.purpleshade.pms.utils.show
import com.purpleshade.pms.utils.snackbar

class CreateNewPassViewModel(val context: Context, val editText: EditText, val imageView: ImageView, val progressBar: ProgressBar, val repository: CreateNewPassRepository) : ViewModel() {

    var password: String? = null
    var confirmPassword: String? = null
    var authListener: AuthListener? = null
    var visiblity = true


    fun resetButtonOnClick(view: View) {
        progressBar.show()
        when {
            password.isNullOrEmpty() -> {
                view.snackbar(context, "Please enter a password", R.color.colorBlackGrey)
                progressBar.hide()
                return
            }
            confirmPassword.isNullOrEmpty() || confirmPassword != password -> {
                view.snackbar(context, "Password did not match", R.color.colorBlackGrey)
                progressBar.hide()
                return
            }
        }

        val repo = repository.createNewPassword(context, editText, progressBar)
        authListener!!.onSuccess(repo)

        repository.view = view

    }

    fun passwordVisibilityOnClick(view: View) {
        if (visiblity) {
            visiblity = false
            editText.transformationMethod = null
            imageView.setImageResource(R.drawable.ic_visibilty_off)
        } else {
            visiblity = true
            editText.transformationMethod = PasswordTransformationMethod()
            imageView.setImageResource(R.drawable.ic_visible)

        }
    }

}
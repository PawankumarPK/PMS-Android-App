package com.purpleshade.pms.fragment.signup

import android.content.Context
import android.text.method.PasswordTransformationMethod
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.lifecycle.ViewModel
import androidx.navigation.findNavController
import com.purpleshade.pms.R
import com.purpleshade.pms.repository.SignupRepository
import com.purpleshade.pms.utils.customInterface.AuthListener
import com.purpleshade.pms.utils.hide
import com.purpleshade.pms.utils.show
import com.purpleshade.pms.utils.snackbar
import com.purpleshade.pms.utils.toast


class SignUpViewModel(val context: Context, private val repository: SignupRepository, val progressBar: ProgressBar, val imageView: ImageView, val editText: EditText) : ViewModel() {

    var username: String? = null
    var email: String? = null
    var password: String? = null
    var confirmPassword: String? = null
    var visiblity = true

    var authListener: AuthListener? = null


    fun registerButtonClick(view: View) {
        progressBar.show()

        when {
            username.isNullOrEmpty() -> {
                view.snackbar(context, "Name is required", R.color.colorBlackGrey)
                progressBar.hide()
                return
            }
            email.isNullOrEmpty() -> {
                view.snackbar(context, "Email is required", R.color.colorBlackGrey)
                progressBar.hide()
                return
            }
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

        val repo = repository.signUp(username!!, email!!, password!!, confirmPassword!!, context, progressBar)
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


    fun onLoginClick(view: View) {
        view.findNavController().navigate(R.id.action_signUpFragment_to_loginFragment)

    }
}
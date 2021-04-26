package com.purpleshade.pms.fragment.login

import android.content.Context
import android.text.method.PasswordTransformationMethod
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.lifecycle.ViewModel
import androidx.navigation.findNavController
import com.purpleshade.pms.R
import com.purpleshade.pms.db.RoomUser
import com.purpleshade.pms.repository.LoginRepository
import com.purpleshade.pms.utils.customInterface.AuthListener
import com.purpleshade.pms.utils.customObject.Flag
import com.purpleshade.pms.utils.hide
import com.purpleshade.pms.utils.show
import com.purpleshade.pms.utils.toast

class LoginViewModel(val context: Context, private val repository: LoginRepository, val progressBar: ProgressBar, val user: RoomUser, val imageView: ImageView, val editText: EditText) : ViewModel() {

    var email: String? = null
    var password: String? = null
    var authListener: AuthListener? = null
    var username: String? = null
    var visiblity = true


    fun onLoginButtonClick(view: View) {
/*
        val listDataClear = BaseActivity.INSTANCE!!.myDao()
        listDataClear.clearTable()
        Log.d("---->>>",listDataClear.toString())
*/
        if (Flag.networkProblem) {
            context.toast(context.getString(R.string.no_internet_connection))
            return
        }

        if (email.isNullOrEmpty() || password.isNullOrEmpty()) {
            progressBar.hide()
            context.toast("Field Empty")
            return
        }
        progressBar.show()
        val repo = repository.doLogin(context,email.toString(), password.toString(), user, view, progressBar)
        authListener!!.onSuccess(repo)
    }

    fun onRegisterClick(view: View) {
        view.findNavController().navigate(R.id.action_loginFragment_to_signUpFragment)

    }

    fun forgotPasswordOnClick(view: View){
        view.findNavController().navigate(R.id.action_loginFragment_to_forgotPasswordFragment)

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
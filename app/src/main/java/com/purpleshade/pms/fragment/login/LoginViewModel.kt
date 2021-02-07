package com.purpleshade.pms.fragment.login

import android.content.Context
import android.view.View
import android.widget.ProgressBar
import androidx.lifecycle.ViewModel
import androidx.navigation.findNavController
import com.purpleshade.pms.R
import com.purpleshade.pms.db.RoomUser
import com.purpleshade.pms.repository.LoginRepository
import com.purpleshade.pms.utils.customInterface.AuthListener
import com.purpleshade.pms.utils.hide
import com.purpleshade.pms.utils.show
import com.purpleshade.pms.utils.toast

class LoginViewModel(val context: Context, private val repository: LoginRepository, val progressBar: ProgressBar, val user: RoomUser) : ViewModel() {

    var email: String? = null
    var password: String? = null
    var authListener: AuthListener? = null
    var username: String? = null

    fun onLoginButtonClick(view: View) {
/*
        val listDataClear = BaseActivity.INSTANCE!!.myDao()
        listDataClear.clearTable()
        Log.d("---->>>",listDataClear.toString())
*/

        progressBar.show()


        if (email.isNullOrEmpty() || password.isNullOrEmpty()) {
            progressBar.hide()
            context.toast("Field Empty")
            return
        }

        val repo = repository.doLogin(context,email.toString(), password.toString(), user, view, progressBar)
        authListener!!.onSuccess(repo)
    }

    fun onRegisterClick(view: View) {
        view.findNavController().navigate(R.id.action_loginFragment_to_signUpFragment)

    }
}
package com.purpleshade.pms.fragment.login

import android.content.Context
import android.view.View
import android.widget.Toast
import androidx.databinding.ObservableInt
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.findNavController
import com.purpleshade.pms.R
import com.purpleshade.pms.model.SignUpModel
import com.purpleshade.pms.network.RetrofitClient
import com.purpleshade.pms.repository.LoginRepository
import com.purpleshade.pms.utils.JWTUtils
import com.purpleshade.pms.utils.customInterface.AuthListener
import com.purpleshade.pms.utils.customObject.UserResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginViewModel(val context: Context, private val repository: LoginRepository) : ViewModel() {

    var email: String? = null
    var password: String? = null
    var authListener: AuthListener? = null
    var progressBar: ObservableInt? = null

    fun onLoginButtonClick(view: View) {
        progressBar!!.set(View.VISIBLE)
        if (email.isNullOrEmpty() || password.isNullOrEmpty()) {
            progressBar!!.set(View.GONE)
            Toast.makeText(context, "Field Empty", Toast.LENGTH_SHORT).show()
            return
        }
        val repo = repository.doLogin(email.toString(), password.toString(), progressBar, context, view)
        authListener!!.onSuccess(repo)

    }

    init {
        progressBar = ObservableInt(View.GONE)
    }
}
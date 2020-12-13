package com.purpleshade.pms.fragment.login

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.purpleshade.pms.R
import com.purpleshade.pms.fragment.BaseFragment
import com.purpleshade.pms.network.signupModel.SignUpModel
import com.purpleshade.pms.network.standardObjects.RetrofitClient
import com.purpleshade.pms.utils.JWTUtils
import kotlinx.android.synthetic.main.login_fragment.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginFragment : BaseFragment() {

    private lateinit var viewModel: LoginViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.login_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)

        baseActivity.toolbar.visibility = View.GONE

        val login = view.findViewById<Button>(R.id.mLogin)
        val register = view.findViewById<TextView>(R.id.mRegister)

        login.setOnClickListener {
            doLogin()
        }
        register.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_signUpFragment)
        }
    }

    private fun doLogin() {
        val email = mUserId.text
        val password = mUserPassword.text

        val api = RetrofitClient.apiService
        val call = api.login(email.toString(), password.toString())

        call.enqueue(object : Callback<SignUpModel> {
            override fun onFailure(call: Call<SignUpModel>?, t: Throwable?) {
                Toast.makeText(baseActivity, "Something went wrong", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<SignUpModel>, response: Response<SignUpModel>) {
                if (response.isSuccessful) {
                    Toast.makeText(baseActivity, "User Login Successfully", Toast.LENGTH_SHORT).show()
                    val res = response.body()!!.message
                    val token = response.body()!!.token
                    JWTUtils.decoded(token!!)
                    Log.d("===>>", token)
                    if (res.equals("User Found"))
                    //findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
                        Toast.makeText(baseActivity, "Login Successfully", Toast.LENGTH_SHORT).show()
                    else
                        Toast.makeText(baseActivity, "Invalid credential", Toast.LENGTH_SHORT).show()
                }

            }
        })
    }
}

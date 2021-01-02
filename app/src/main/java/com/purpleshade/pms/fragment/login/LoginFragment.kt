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
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.purpleshade.pms.R
import com.purpleshade.pms.databinding.LoginFragmentBinding
import com.purpleshade.pms.fragment.BaseFragment
import com.purpleshade.pms.model.SignUpModel
import com.purpleshade.pms.network.RetrofitClient
import com.purpleshade.pms.utils.JWTUtils
import com.purpleshade.pms.utils.customInterface.AuthListener
import kotlinx.android.synthetic.main.login_fragment.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginFragment : BaseFragment(), AuthListener {

    private lateinit var viewModel: LoginViewModel
    var token = ""
    var tokenDetail = ""
    lateinit var binding: LoginFragmentBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.login_fragment, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
        binding.viewModel = viewModel
        viewModel.authListener = this

        baseActivity.toolbar.visibility = View.GONE

        /* val login = view.findViewById<Button>(R.id.mLogin)
         val register = view.findViewById<TextView>(R.id.mRegister)

         login.setOnClickListener {
             doLogin()
         }
         register.setOnClickListener {
             findNavController().navigate(R.id.action_loginFragment_to_signUpFragment)
         }*/
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
                    token = response.body()!!.token.toString()
                    JWTUtils.decoded(token)
                    JWTUtils.parseUserDetail()
                    if (res.equals("User Found"))
                        findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
                    else
                        Toast.makeText(baseActivity, "Invalid credential", Toast.LENGTH_SHORT).show()
                }

            }
        })

    }

    override fun onStarted() {
        Log.d("===>>","Started")
        mProgressBar.visibility = View.VISIBLE
        Toast.makeText(baseActivity, "Login Started", Toast.LENGTH_SHORT).show()
    }

    override fun onSuccess(responseSuccess: LiveData<String>) {
        Log.d("===>>","Success")
        responseSuccess.observe(this, Observer {
            mProgressBar.visibility = View.GONE
            Toast.makeText(baseActivity, it, Toast.LENGTH_SHORT).show()
        })
    }

    override fun onFailed(message: String) {
        Log.d("===>>","Failed")
        mProgressBar.visibility = View.GONE
        Toast.makeText(baseActivity, message, Toast.LENGTH_SHORT).show()
    }
}

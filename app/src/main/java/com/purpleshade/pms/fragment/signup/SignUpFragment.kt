package com.purpleshade.pms.fragment.signup

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.purpleshade.pms.R
import com.purpleshade.pms.fragment.BaseFragment
import com.purpleshade.pms.model.SignUpModel
import com.purpleshade.pms.network.RetrofitClient
import kotlinx.android.synthetic.main.sign_up_fragment.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignUpFragment : BaseFragment() {

    private lateinit var viewModel: SignUpViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.sign_up_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(SignUpViewModel::class.java)

        val login = view.findViewById<TextView>(R.id.mLogin)
        login.setOnClickListener {
            findNavController().navigate(R.id.loginFragment)
        }
        mRegister.setOnClickListener {
            signUp()
        }
    }

    private fun signUp() {
        val username = mUsername.text
        val email = mEmail.text
        val password = mPassword.text
        val confirmPass = mConfirmPassword.text

        Log.d("====>>>", "$username $email $password $confirmPass")

        val api = RetrofitClient.apiService
        val call = api.signUp(username.toString(), email.toString(), password.toString(),confirmPass.toString())

        call.enqueue(object : Callback<SignUpModel> {
            override fun onFailure(call: Call<SignUpModel>?, t: Throwable?) {
                Toast.makeText(baseActivity, "Something went wrong", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<SignUpModel>, response: Response<SignUpModel>) {
                Toast.makeText(baseActivity, "User Register Successfully", Toast.LENGTH_SHORT).show()
                // floors = response.body().floors!!
                // addFloorButtons()
            }
        })
    }

}
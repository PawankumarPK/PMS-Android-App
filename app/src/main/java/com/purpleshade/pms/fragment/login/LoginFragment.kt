package com.purpleshade.pms.fragment.login

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.purpleshade.pms.R
import com.purpleshade.pms.activity.BaseActivity
import com.purpleshade.pms.fragment.BaseFragment

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
            Toast.makeText(baseActivity, "Clicked", Toast.LENGTH_SHORT).show()
        }
        register.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_signUpFragment)
        }
        //baseActivity.mToolbar.visibility = View.VISIBLE
    }
}

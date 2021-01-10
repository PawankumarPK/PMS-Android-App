package com.purpleshade.pms.fragment.login

import android.content.Intent
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
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.purpleshade.pms.R
import com.purpleshade.pms.databinding.LoginFragmentBinding
import com.purpleshade.pms.fragment.BaseFragment
import com.purpleshade.pms.model.SignUpModel
import com.purpleshade.pms.network.RetrofitClient
import com.purpleshade.pms.repository.LoginRepository
import com.purpleshade.pms.utils.JWTUtils
import com.purpleshade.pms.utils.customInterface.AuthListener
import com.purpleshade.pms.utils.customObject.UserResponse
import com.purpleshade.pms.utils.toast
import kotlinx.android.synthetic.main.activity_base.*
import kotlinx.android.synthetic.main.login_fragment.*
import okhttp3.internal.Internal.instance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginFragment : BaseFragment(), AuthListener {
    private lateinit var viewModel: LoginViewModel
    lateinit var binding: LoginFragmentBinding
    lateinit var v: View

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.login_fragment, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val repository = LoginRepository()
        val factory = AuthViewModelFactory(baseActivity,repository)
        viewModel = ViewModelProvider(this, factory).get(LoginViewModel::class.java)
        viewModel.authListener = this
        binding.viewModel = viewModel
        v = view
        baseActivity.mToolbar.visibility = View.GONE

    }

    override fun onSuccess(responseSuccess: LiveData<String>) {
        responseSuccess.observe(this, Observer {
           // Toast.makeText(baseActivity, it, Toast.LENGTH_SHORT).show()
        })
    }


}

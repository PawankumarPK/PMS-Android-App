package com.purpleshade.pms.fragment.login.forgotPassword

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.purpleshade.pms.R
import com.purpleshade.pms.databinding.ForgotPasswordFragmentBinding
import com.purpleshade.pms.fragment.BaseFragment
import com.purpleshade.pms.fragment.login.AuthViewModelFactory
import com.purpleshade.pms.fragment.login.LoginViewModel
import com.purpleshade.pms.repository.LoginRepository
import com.purpleshade.pms.utils.customInterface.AuthListener
import kotlinx.android.synthetic.main.activity_base.*
import kotlinx.android.synthetic.main.login_fragment.*

class ForgotPasswordFragment : BaseFragment(),AuthListener{

    private lateinit var viewModel: ForgotPasswordViewModel
    lateinit var binding : ForgotPasswordFragmentBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.forgot_password_fragment, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //val repository = LoginRepository()
        val factory = ForgotPasswordViewModelFactory()
        viewModel = ViewModelProvider(this,factory).get(ForgotPasswordViewModel::class.java)
        binding.viewModel  = viewModel

    }

    override fun onSuccess(responseSuccess: LiveData<String>) {
        responseSuccess.observe(this, Observer {

        })
    }

}
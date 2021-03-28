package com.purpleshade.pms.fragment.login.forgotPassword.createNewPassword

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
import com.purpleshade.pms.databinding.CreateNewPassFragmentBinding
import com.purpleshade.pms.databinding.ForgotPasswordFragmentBinding
import com.purpleshade.pms.fragment.BaseFragment
import com.purpleshade.pms.fragment.login.forgotPassword.ForgotPasswordViewModel
import com.purpleshade.pms.fragment.login.forgotPassword.ForgotPasswordViewModelFactory
import com.purpleshade.pms.repository.CreateNewPassRepository
import com.purpleshade.pms.repository.ForgotPasswordRepository
import com.purpleshade.pms.utils.customInterface.AuthListener
import kotlinx.android.synthetic.main.create_new_pass_fragment.*

class CreateNewPassFragment : BaseFragment(), AuthListener {

    private lateinit var viewModel: CreateNewPassViewModel
    lateinit var binding: CreateNewPassFragmentBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.create_new_pass_fragment, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val repository = CreateNewPassRepository()
        val factory = CreateNewPassViewModelFactory(baseActivity, mPassword, repository)
        viewModel = ViewModelProvider(this, factory).get(CreateNewPassViewModel::class.java)
        binding.viewModel = viewModel
        viewModel.authListener = this
    }


    override fun onSuccess(responseSuccess: LiveData<String>) {
        responseSuccess.observe(this, Observer {

        })
    }

}
package com.purpleshade.pms.fragment.login.forgotPassword.verification

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.purpleshade.pms.R
import com.purpleshade.pms.databinding.ForgotPasswordFragmentBinding
import com.purpleshade.pms.databinding.FpVerificationFragmentBinding
import com.purpleshade.pms.fragment.BaseFragment
import com.purpleshade.pms.fragment.login.forgotPassword.ForgotPasswordViewModel
import com.purpleshade.pms.fragment.login.forgotPassword.ForgotPasswordViewModelFactory

class FpVerificationFragment : BaseFragment() {

    private lateinit var viewModel: FpVerificationViewModel
    lateinit var binding : FpVerificationFragmentBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fp_verification_fragment, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val factory = FpVerificationViewModelFactory()
        viewModel = ViewModelProvider(this,factory).get(FpVerificationViewModel::class.java)
        binding.viewModel  = viewModel

    }

}
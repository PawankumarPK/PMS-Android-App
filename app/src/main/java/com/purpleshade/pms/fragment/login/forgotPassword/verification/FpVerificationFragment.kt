package com.purpleshade.pms.fragment.login.forgotPassword.verification

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
import com.purpleshade.pms.databinding.FpVerificationFragmentBinding
import com.purpleshade.pms.fragment.BaseFragment
import com.purpleshade.pms.fragment.login.forgotPassword.ForgotPasswordViewModel
import com.purpleshade.pms.fragment.login.forgotPassword.ForgotPasswordViewModelFactory
import com.purpleshade.pms.repository.FpVerificationRepository
import com.purpleshade.pms.utils.customInterface.AuthListener
import kotlinx.android.synthetic.main.activity_base.*
import kotlinx.android.synthetic.main.verification_fragment.*

class FpVerificationFragment : BaseFragment(), AuthListener {

    private lateinit var viewModel: FpVerificationViewModel
    lateinit var binding: FpVerificationFragmentBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fp_verification_fragment, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val repository = FpVerificationRepository()
        val factory = FpVerificationViewModelFactory(baseActivity,mEditTextBoxOne, mEditTextBoxTwo, mEditTextBoxThree, mEditTextBoxFour, mEditTextBoxFive, mEditTextBoxSix,baseActivity.mProgressBar,repository)
        viewModel = ViewModelProvider(this, factory).get(FpVerificationViewModel::class.java)
        binding.viewModel = viewModel
        viewModel.authListener = this
        viewModel.createPinBoxes()
        viewModel.getVerificationToken()

    }

    override fun onSuccess(responseSuccess: LiveData<String>) {
        responseSuccess.observe(this, Observer {

        })
    }


}
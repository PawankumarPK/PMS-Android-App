package com.purpleshade.pms.fragment.signup.verification

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.purpleshade.pms.R
import com.purpleshade.pms.databinding.VerificationFragmentBinding
import com.purpleshade.pms.fragment.BaseFragment
import com.purpleshade.pms.repository.SignupVerificationRepository
import com.purpleshade.pms.utils.customInterface.AuthListener
import kotlinx.android.synthetic.main.activity_base.*
import kotlinx.android.synthetic.main.verification_fragment.*

class VerificationFragment : BaseFragment(), AuthListener {

    private lateinit var viewModel: VerificationViewModel
    private lateinit var binding: VerificationFragmentBinding


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.verification_fragment, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val repository = SignupVerificationRepository()
        val factory = VerificationViewModelFactory(baseActivity, mEditTextBoxOne, mEditTextBoxTwo, mEditTextBoxThree, mEditTextBoxFour, mEditTextBoxFive, mEditTextBoxSix, baseActivity.mProgressBar, repository)
        viewModel = ViewModelProvider(this, factory).get(VerificationViewModel::class.java)
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
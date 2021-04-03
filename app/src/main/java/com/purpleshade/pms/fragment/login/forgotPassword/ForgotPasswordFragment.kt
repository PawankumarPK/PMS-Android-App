package com.purpleshade.pms.fragment.login.forgotPassword

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.purpleshade.pms.R
import com.purpleshade.pms.databinding.ForgotPasswordFragmentBinding
import com.purpleshade.pms.fragment.BaseFragment
import com.purpleshade.pms.repository.ForgotPasswordRepository
import com.purpleshade.pms.utils.customInterface.AuthListener
import com.purpleshade.pms.utils.gone
import com.purpleshade.pms.utils.show
import kotlinx.android.synthetic.main.activity_base.*

class ForgotPasswordFragment : BaseFragment(), AuthListener {

    private lateinit var viewModel: ForgotPasswordViewModel
    lateinit var binding: ForgotPasswordFragmentBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.forgot_password_fragment, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val repository = ForgotPasswordRepository()
        val factory = ForgotPasswordViewModelFactory(baseActivity, baseActivity.mProgressBar, repository)
        viewModel = ViewModelProvider(this, factory).get(ForgotPasswordViewModel::class.java)
        binding.viewModel = viewModel
        viewModel.authListener = this
        viewModel.appPinEvent()


    }

    override fun onSuccess(responseSuccess: LiveData<String>) {
        responseSuccess.observe(this, Observer {

        })
    }

}
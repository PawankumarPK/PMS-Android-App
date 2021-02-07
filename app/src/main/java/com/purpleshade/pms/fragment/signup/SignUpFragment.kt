package com.purpleshade.pms.fragment.signup

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.purpleshade.pms.R
import com.purpleshade.pms.databinding.SignUpFragmentBinding
import com.purpleshade.pms.fragment.BaseFragment
import com.purpleshade.pms.model.SignUpModel
import com.purpleshade.pms.network.RetrofitClient
import com.purpleshade.pms.repository.SignupRepository
import com.purpleshade.pms.utils.customInterface.AuthListener
import kotlinx.android.synthetic.main.activity_base.*
import kotlinx.android.synthetic.main.sign_up_fragment.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignUpFragment : BaseFragment(), AuthListener {

    private lateinit var viewModel: SignUpViewModel
    lateinit var binding: SignUpFragmentBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.sign_up_fragment,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = this
        val repository = SignupRepository()
        val factory = SignUpViewModelFactory(baseActivity,repository,baseActivity.mProgressBar,mPasswordVisiblity,mPassword)
        viewModel = ViewModelProvider(this,factory).get(SignUpViewModel::class.java)
        viewModel.authListener = this
        binding.viewModel = viewModel

    }
    override fun onSuccess(responseSuccess: LiveData<String>) {
        responseSuccess.observe(this, Observer {

        })
    }

}
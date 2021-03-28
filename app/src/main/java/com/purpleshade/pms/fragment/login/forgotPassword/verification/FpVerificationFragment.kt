package com.purpleshade.pms.fragment.login.forgotPassword.verification

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.purpleshade.pms.R

class FpVerificationFragment : Fragment() {

    companion object {
        fun newInstance() = FpVerificationFragment()
    }

    private lateinit var viewModel: FpVerificationViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fp_verification_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(FpVerificationViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
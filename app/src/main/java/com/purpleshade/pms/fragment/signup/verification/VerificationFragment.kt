package com.purpleshade.pms.fragment.signup.verification

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.purpleshade.pms.R
import com.purpleshade.pms.databinding.VerificationFragmentBinding
import com.purpleshade.pms.fragment.BaseFragment
import kotlinx.android.synthetic.main.verification_fragment.*

class VerificationFragment : BaseFragment() {

    private lateinit var viewModel: VerificationViewModel
    private lateinit var binding: VerificationFragmentBinding


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.verification_fragment, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val factory = VerificationViewModelFactory(mEditTextBoxOne, mEditTextBoxTwo, mEditTextBoxThree, mEditTextBoxFour, mEditTextBoxFive, mEditTextBoxSix)
        viewModel = ViewModelProvider(this, factory).get(VerificationViewModel::class.java)
        binding.viewModel = viewModel
        viewModel.createPinBoxes()


    }


}
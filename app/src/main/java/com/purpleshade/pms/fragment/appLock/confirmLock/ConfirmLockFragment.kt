package com.purpleshade.pms.fragment.appLock.confirmLock

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.purpleshade.pms.R
import com.purpleshade.pms.databinding.ConfirmLockFragmentBinding
import com.purpleshade.pms.fragment.BaseFragment
import com.purpleshade.pms.fragment.appLock.createLock.CreateLockViewModel
import com.purpleshade.pms.fragment.appLock.createLock.CreateLockViewModelFactory
import com.purpleshade.pms.utils.gone
import kotlinx.android.synthetic.main.activity_base.*
import kotlinx.android.synthetic.main.create_lock_fragment.*

class ConfirmLockFragment : BaseFragment() {

    private lateinit var viewModel: ConfirmLockViewModel
    lateinit var binding: ConfirmLockFragmentBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.confirm_lock_fragment, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val factory = ConfirmLockViewModelFactory(view, mEditTextBoxOne, mEditTextBoxTwo, mEditTextBoxThree, mEditTextBoxFour)
        viewModel = ViewModelProvider(this, factory).get(ConfirmLockViewModel::class.java)
        binding.viewModel = viewModel
        viewModel.confirmPinBoxes()

    }

}
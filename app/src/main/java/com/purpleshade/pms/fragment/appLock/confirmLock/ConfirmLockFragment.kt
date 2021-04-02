package com.purpleshade.pms.fragment.appLock.confirmLock

import android.R.attr
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.purpleshade.pms.R
import com.purpleshade.pms.databinding.ConfirmLockFragmentBinding
import com.purpleshade.pms.fragment.BaseFragment
import com.purpleshade.pms.repository.ConfirmLockRepository
import com.purpleshade.pms.utils.customInterface.AuthListener
import kotlinx.android.synthetic.main.activity_base.*
import kotlinx.android.synthetic.main.confirm_lock_fragment.*
import kotlinx.android.synthetic.main.create_lock_fragment.*
import kotlinx.android.synthetic.main.create_lock_fragment.mEditTextBoxFour
import kotlinx.android.synthetic.main.create_lock_fragment.mEditTextBoxOne
import kotlinx.android.synthetic.main.create_lock_fragment.mEditTextBoxThree
import kotlinx.android.synthetic.main.create_lock_fragment.mEditTextBoxTwo


class ConfirmLockFragment : BaseFragment(), AuthListener {

    private lateinit var viewModel: ConfirmLockViewModel
    lateinit var binding: ConfirmLockFragmentBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.confirm_lock_fragment, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val repository = ConfirmLockRepository()
        val factory = ConfirmLockViewModelFactory(baseActivity, view, mEditTextBoxOne, mEditTextBoxTwo, mEditTextBoxThree, mEditTextBoxFour, mCreatePin, baseActivity.mProgressBar, repository)
        viewModel = ViewModelProvider(this, factory).get(ConfirmLockViewModel::class.java)
        binding.viewModel = viewModel
        viewModel.authListener = this
        viewModel.confirmPinBoxes()

    }

    override fun onSuccess(responseSuccess: LiveData<String>) {
        responseSuccess.observe(this, Observer {

        })
    }


}
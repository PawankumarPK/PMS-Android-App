package com.purpleshade.pms.fragment.enablePassword

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.purpleshade.pms.R
import com.purpleshade.pms.databinding.AppPasswordFragmentBinding
import com.purpleshade.pms.databinding.EnablePasswordFragmentBinding
import com.purpleshade.pms.fragment.BaseFragment
import com.purpleshade.pms.fragment.appPassword.AppPasswordViewModel
import com.purpleshade.pms.fragment.appPassword.AppPasswordViewModelFactory
import com.purpleshade.pms.utils.GenericTextWatcher
import kotlinx.android.synthetic.main.app_password_fragment.*
import kotlinx.android.synthetic.main.enable_password_fragment.*


class EnablePasswordFragment : BaseFragment() {

    private lateinit var viewModel: EnablePasswordViewModel
    lateinit var binding: EnablePasswordFragmentBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.enable_password_fragment, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val factory = EnablePasswordViewModelFactory(view,mEditTextBoxOne, mEditTextBoxTwo, mEditTextBoxThree, mEditTextBoxFour)
        viewModel = ViewModelProvider(this, factory).get(EnablePasswordViewModel::class.java)
        binding.viewModel = viewModel
        viewModel.password()


       /* val edit = arrayOf<EditText>(otp_edit_box1, otp_edit_box2, otp_edit_box3, otp_edit_box4)

        otp_edit_box1.addTextChangedListener(GenericTextWatcher(otp_edit_box1, edit))
        otp_edit_box2.addTextChangedListener(GenericTextWatcher(otp_edit_box2, edit))
        otp_edit_box3.addTextChangedListener(GenericTextWatcher(otp_edit_box3, edit))
        otp_edit_box4.addTextChangedListener(GenericTextWatcher(otp_edit_box4, edit))*/

    }

}
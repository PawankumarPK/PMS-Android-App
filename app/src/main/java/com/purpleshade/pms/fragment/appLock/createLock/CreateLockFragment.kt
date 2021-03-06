package com.purpleshade.pms.fragment.appLock.createLock

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.purpleshade.pms.R
import com.purpleshade.pms.databinding.CreateLockFragmentBinding
import com.purpleshade.pms.fragment.BaseFragment
import com.purpleshade.pms.utils.gone
import kotlinx.android.synthetic.main.activity_base.*
import kotlinx.android.synthetic.main.create_lock_fragment.*


class CreateLockFragment : BaseFragment() {

    private lateinit var viewModel: CreateLockViewModel
    lateinit var binding: CreateLockFragmentBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.create_lock_fragment, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val factory = CreateLockViewModelFactory(view, mEditTextBoxOne, mEditTextBoxTwo, mEditTextBoxThree, mEditTextBoxFour)
        viewModel = ViewModelProvider(this, factory).get(CreateLockViewModel::class.java)
        binding.viewModel = viewModel
        viewModel.createPinBoxes()
        baseActivity.mToolbar.gone()


        /* val edit = arrayOf<EditText>(otp_edit_box1, otp_edit_box2, otp_edit_box3, otp_edit_box4)

         otp_edit_box1.addTextChangedListener(GenericTextWatcher(otp_edit_box1, edit))
         otp_edit_box2.addTextChangedListener(GenericTextWatcher(otp_edit_box2, edit))
         otp_edit_box3.addTextChangedListener(GenericTextWatcher(otp_edit_box3, edit))
         otp_edit_box4.addTextChangedListener(GenericTextWatcher(otp_edit_box4, edit))*/

    }

}
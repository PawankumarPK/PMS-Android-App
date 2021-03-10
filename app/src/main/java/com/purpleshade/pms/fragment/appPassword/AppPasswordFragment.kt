package com.purpleshade.pms.fragment.appPassword

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import androidx.core.content.ContextCompat.getSystemService
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.purpleshade.pms.R
import com.purpleshade.pms.databinding.AppPasswordFragmentBinding
import com.purpleshade.pms.db.RoomUser
import com.purpleshade.pms.fragment.BaseFragment
import com.purpleshade.pms.utils.gone
import kotlinx.android.synthetic.main.activity_base.*
import kotlinx.android.synthetic.main.app_password_fragment.*


class AppPasswordFragment : BaseFragment() {

    private lateinit var viewModel: AppPasswordViewModel
    lateinit var binding : AppPasswordFragmentBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.app_password_fragment, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val user = RoomUser()
        val factory = AppPasswordViewModelFactory(baseActivity,mEditText,user)
        viewModel = ViewModelProvider(this,factory).get(AppPasswordViewModel::class.java)
        binding.viewModel  = viewModel

      //  baseActivity.window.addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)

        baseActivity.mToolbar.gone()

    }
}
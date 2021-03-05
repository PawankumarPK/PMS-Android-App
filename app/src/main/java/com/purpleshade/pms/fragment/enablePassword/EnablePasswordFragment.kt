package com.purpleshade.pms.fragment.enablePassword

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.purpleshade.pms.R
import com.purpleshade.pms.fragment.BaseFragment

class EnablePasswordFragment : BaseFragment() {

    companion object {
        fun newInstance() = EnablePasswordFragment()
    }

    private lateinit var viewModel: EnablePasswordViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.enable_password_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(EnablePasswordViewModel::class.java)
    }

}
package com.purpleshade.pms.fragment.login.forgotPassword.createNewPassword

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.purpleshade.pms.R

class CreateNewPassFragment : Fragment() {

    companion object {
        fun newInstance() = CreateNewPassFragment()
    }

    private lateinit var viewModel: CreateNewPassViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.create_new_pass_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(CreateNewPassViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
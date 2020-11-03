package com.purpleshade.pms.fragment.createRecord

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController
import com.purpleshade.pms.R
import com.purpleshade.pms.fragment.BaseFragment

class CreateRecordFragment : BaseFragment() {

    private lateinit var viewModel: CreateRecordViewModel
    lateinit var done: Button

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.create_record_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(CreateRecordViewModel::class.java)
        baseActivity.fragmentTitle.text = "Create New Record"
        baseActivity.backButton.visibility = View.VISIBLE

        done = view.findViewById(R.id.mDone)

        baseActivity.backButton.setOnClickListener {
            findNavController().navigate(R.id.homeFragment)
        }

        done.setOnClickListener {
            findNavController().navigate(R.id.homeFragment)
        }
    }

}
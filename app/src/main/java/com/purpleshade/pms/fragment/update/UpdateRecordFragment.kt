package com.purpleshade.pms.fragment.update

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.*
import androidx.navigation.fragment.findNavController
import com.purpleshade.pms.R
import com.purpleshade.pms.databinding.UpdateRecordFragmentBinding
import com.purpleshade.pms.fragment.BaseFragment
import com.purpleshade.pms.repository.UpdateRecordRepository
import com.purpleshade.pms.utils.customInterface.AuthListener
import kotlinx.android.synthetic.main.activity_base.*
import kotlinx.android.synthetic.main.update_record_fragment.*


class UpdateRecordFragment : BaseFragment(), AuthListener {

    private lateinit var viewModel: UpdateRecordViewModel
    lateinit var binding: UpdateRecordFragmentBinding
    val repository = UpdateRecordRepository()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.update_record_fragment, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = this
        val factory = UpdateViewModelFactory(baseActivity, repository)
        viewModel = ViewModelProvider(this, factory).get(UpdateRecordViewModel::class.java)
        viewModel.authListener = this
        binding.viewModel = viewModel

        baseActivity.mFragmentTitle.text = getString(R.string.updateRecord)
        baseActivity.mBackButton.visibility = View.VISIBLE

        viewModel.getRecordDetails(mTitle,mWebAddress,mEmail,mPassword,mAddNote)

        baseActivity.mBackButton.setOnClickListener {
            findNavController().navigate(R.id.homeFragment)
        }
    }

    override fun onSuccess(responseSuccess: LiveData<String>) {
        responseSuccess.observe(this, Observer {

        })
    }
}
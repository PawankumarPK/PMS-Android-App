package com.purpleshade.pms.fragment.createRecord

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.purpleshade.pms.R
import com.purpleshade.pms.databinding.CreateRecordFragmentBinding
import com.purpleshade.pms.fragment.BaseFragment
import com.purpleshade.pms.model.SignUpModel
import com.purpleshade.pms.network.RetrofitClient
import com.purpleshade.pms.repository.CreateRecordRepository
import com.purpleshade.pms.utils.customInterface.AuthListener
import com.purpleshade.pms.utils.gone
import com.purpleshade.pms.utils.show
import kotlinx.android.synthetic.main.activity_base.*
import kotlinx.android.synthetic.main.create_record_fragment.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CreateRecordFragment : BaseFragment(),AuthListener {

    private lateinit var viewModel: CreateRecordViewModel
    lateinit var binding : CreateRecordFragmentBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.create_record_fragment,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val repository = CreateRecordRepository()
        val factory = CreateRecordViewModelFactory(baseActivity,repository,baseActivity.mProgressBar,mCheckBox,mLockNoteInputLayout)
        viewModel = ViewModelProvider(this,factory).get(CreateRecordViewModel::class.java)
        viewModel.authListener = this
        binding.viewModel = viewModel

        baseActivity.mFragmentTitle.text = getString(R.string.createNewRecord)
        baseActivity.mToolbar.show()
        baseActivity.mBackButton.show()
        baseActivity.mProfileImageView.gone()
        baseActivity.mBackButton.setOnClickListener {
            findNavController().navigate(R.id.homeFragment)
        }
    }

    override fun onSuccess(responseSuccess: LiveData<String>) {
        responseSuccess.observe(this, Observer {

        })
    }

}
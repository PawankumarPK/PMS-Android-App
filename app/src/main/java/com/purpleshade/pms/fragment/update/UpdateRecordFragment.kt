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

        //mUpdate.setOnClickListener { updateRecord() }
        viewModel.getRecordDetails(mTitle,mWebAddress,mEmail,mPassword,mAddNote)
        baseActivity.mBackButton.setOnClickListener {
            findNavController().navigate(R.id.homeFragment)
        }
    }

    override fun onSuccess(responseSuccess: LiveData<String>) {
        responseSuccess.observe(this, Observer {

        })
    }

    /*private fun getRecordDetails() {
        val api = RetrofitClient.apiService
        val call = api.recordDetail(RecordDetail.recordId)

        call.enqueue(object : Callback<Records> {
            override fun onFailure(call: Call<Records>, t: Throwable) {
                Toast.makeText(baseActivity, "Something went wrong", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<Records>, response: Response<Records>) {
                if (response.isSuccessful) {
                    Toast.makeText(baseActivity, "Get Record Successfully", Toast.LENGTH_SHORT).show()
                    val msg = response.body()!!.message
                    val recordDetail = response.body()!!.recordDetail

                    for (i in recordDetail) {
                        val title = i.title
                        val webAddress = i.websiteAddress
                        val email = i.email
                        val password = i.password
                        val addNote = i.addNote

                        mTitle.setText(title)
                        mWebAddress.setText(webAddress)
                        mEmail.setText(email)
                        mPassword.setText(password)
                        mAddNote.setText(addNote)

                    }

                }
            }

        })
    }

    private fun updateRecord() {
        val title = mTitle.text.toString()
        val webAddress = mWebAddress.text.toString()
        val email = mEmail.text.toString()
        val password = mPassword.text.toString()
        val addNote = mAddNote.text.toString()

        val update = UpdateRecord(title, webAddress, email, password, addNote)

        val api = RetrofitClient.apiService
        val call = api.updateRecord(RecordDetail.recordId, update)

        call.enqueue(object : Callback<SignUpModel> {
            override fun onFailure(call: Call<SignUpModel>, t: Throwable) {
                Toast.makeText(baseActivity, "Something went wrong", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<SignUpModel>, response: Response<SignUpModel>) {
                Toast.makeText(baseActivity, "Record Update Successfully", Toast.LENGTH_SHORT).show()
            }

        })
    }
*/
}
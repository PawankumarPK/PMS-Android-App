package com.purpleshade.pms.fragment.update

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.purpleshade.pms.R
import com.purpleshade.pms.fragment.BaseFragment
import com.purpleshade.pms.network.signupModel.SignUpModel
import com.purpleshade.pms.network.standardObjects.RetrofitClient
import com.purpleshade.pms.utils.Records
import com.purpleshade.pms.utils.customObject.UpdateRecord
import com.purpleshade.pms.utils.customObject.RecordDetail
import kotlinx.android.synthetic.main.update_record_fragment.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class UpdateRecordFragment : BaseFragment() {

    private lateinit var viewModel: UpdateRecordViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.update_record_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(UpdateRecordViewModel::class.java)
        baseActivity.fragmentTitle.text = getString(R.string.updateRecord)
        baseActivity.backButton.visibility = View.VISIBLE

        getRecordDetails()
        mUpdate.setOnClickListener { updateRecord() }
        baseActivity.backButton.setOnClickListener {
            findNavController().navigate(R.id.homeFragment)
        }

    }

    private fun getRecordDetails() {
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

}
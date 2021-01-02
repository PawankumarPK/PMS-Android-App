package com.purpleshade.pms.fragment.createRecord

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.purpleshade.pms.R
import com.purpleshade.pms.fragment.BaseFragment
import com.purpleshade.pms.model.SignUpModel
import com.purpleshade.pms.network.RetrofitClient
import kotlinx.android.synthetic.main.create_record_fragment.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CreateRecordFragment : BaseFragment() {

    private lateinit var viewModel: CreateRecordViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.create_record_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(CreateRecordViewModel::class.java)
        baseActivity.fragmentTitle.text = getString(R.string.createNewRecord)
        baseActivity.backButton.visibility = View.VISIBLE

        baseActivity.backButton.setOnClickListener {
            findNavController().navigate(R.id.homeFragment)
        }

        mDone.setOnClickListener {
            fillRecordDetails()
            findNavController().navigate(R.id.homeFragment)
        }
    }

    private fun fillRecordDetails() {

        val title = mTitle.text.toString()
        val webAddress = mWebAddress.text.toString()
        val email = mEmail.text.toString()
        val password = mPassword.text.toString()
        val addNote = mAddNote.text.toString()

        val api = RetrofitClient.apiService
        val call = api.addRecordDetail(title, webAddress, email, password, addNote)

        call.enqueue(object : Callback<SignUpModel> {
            override fun onFailure(call: Call<SignUpModel>, t: Throwable) {
                Toast.makeText(baseActivity, "Something went wrong", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<SignUpModel>, response: Response<SignUpModel>) {
                Toast.makeText(baseActivity, "Add Record Successfully", Toast.LENGTH_SHORT).show()
            }

        })
    }

}
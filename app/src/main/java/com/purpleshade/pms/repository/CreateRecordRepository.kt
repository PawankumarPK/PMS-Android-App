package com.purpleshade.pms.repository

import android.content.Context
import android.view.View
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.findNavController
import com.purpleshade.pms.R
import com.purpleshade.pms.model.SignUpModel
import com.purpleshade.pms.network.RetrofitClient
import com.purpleshade.pms.utils.toast
import kotlinx.android.synthetic.main.create_record_fragment.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by pawan on 17,January,2021
 */
class CreateRecordRepository : ViewModel() {

    fun fillRecordDetails(context: Context, view: View, progressBar: MutableLiveData<Boolean> ,title: String, webAddress: String, email: String, password: String, addNote: String): LiveData<String> {
        val responseAddData: MutableLiveData<String> = MutableLiveData()

        val api = RetrofitClient.apiService
        val call = api.addRecordDetail(title, webAddress, email, password, addNote)

        call.enqueue(object : Callback<SignUpModel> {
            override fun onFailure(call: Call<SignUpModel>, t: Throwable) {
                progressBar.postValue(false)
                context.toast("Something went wrong")
            }

            override fun onResponse(call: Call<SignUpModel>, response: Response<SignUpModel>) {
                progressBar.postValue(false)
                context.toast("Add Record Successfully")
                view.findNavController().navigate(R.id.action_createRecordFragment_to_homeFragment)

            }

        })

        return responseAddData
    }
}
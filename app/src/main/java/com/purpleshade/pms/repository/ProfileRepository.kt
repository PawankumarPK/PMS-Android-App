package com.purpleshade.pms.repository

import android.content.Context
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.purpleshade.pms.R
import com.purpleshade.pms.activity.BaseActivity
import com.purpleshade.pms.model.Profile
import com.purpleshade.pms.model.SignUpModel
import com.purpleshade.pms.model.UpdateProfile
import com.purpleshade.pms.network.RetrofitClient
import com.purpleshade.pms.utils.customObject.RoomRecordDetail
import com.purpleshade.pms.utils.hide
import com.purpleshade.pms.utils.snackbar
import com.purpleshade.pms.utils.toast
import org.w3c.dom.Text
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by pawan on 28,February,2021
 */
class ProfileRepository {

    fun profileDetails(context: Context, view: View, progressBar: ProgressBar,username: TextView, email: TextView, nickname: TextView): LiveData<String> {
        val responseForProfileDetails = MutableLiveData<String>()
        val api = RetrofitClient.apiService
        val call = api.userProfile(RoomRecordDetail.userId)

        call.enqueue(object : Callback<Profile> {
            override fun onFailure(call: Call<Profile>, t: Throwable) {
                view.snackbar(context, R.string.something_went_wrong.toString(), R.color.colorWarning)
                progressBar.hide()
            }

            override fun onResponse(call: Call<Profile>, response: Response<Profile>) {
                if (response.isSuccessful) {
                    progressBar.hide()
                    val list = response.body()!!.data
                    for (i in list.indices) {
                        username.text = list[i].username
                        email.text = list[i].email
                        nickname.text = list[i].username

                    }
                }
            }

        })

        return responseForProfileDetails

    }
}
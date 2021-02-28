package com.purpleshade.pms.repository

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.purpleshade.pms.activity.BaseActivity
import com.purpleshade.pms.db.RoomUser
import com.purpleshade.pms.model.SignUpModel
import com.purpleshade.pms.model.UpdateProfile
import com.purpleshade.pms.network.RetrofitClient
import com.purpleshade.pms.utils.customObject.RoomRecordDetail
import com.purpleshade.pms.utils.toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by pawan on 28,February,2021
 */
class EditProfileRepository {

    fun updateProfile(context: Context, username: String, email: String): LiveData<String> {
        val responseForUpdateProfile = MutableLiveData<String>()
        val updateProfile = UpdateProfile(username, email)
        val api = RetrofitClient.apiService
        val call = api.updateProfile(RoomRecordDetail.userId, updateProfile)

        call.enqueue(object : Callback<SignUpModel> {
            override fun onFailure(call: Call<SignUpModel>, t: Throwable) {
                context.toast("Failed")
            }

            override fun onResponse(call: Call<SignUpModel>, response: Response<SignUpModel>) {
                val roomUser = BaseActivity.INSTANCE!!.myDao().user
                roomUser.username = username
                roomUser.email = email
                context.toast("Success")
            }

        })

        return responseForUpdateProfile

    }
}
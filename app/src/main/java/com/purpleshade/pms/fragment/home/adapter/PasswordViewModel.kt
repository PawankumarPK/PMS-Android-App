package com.purpleshade.pms.fragment.home.adapter

import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModel
import androidx.navigation.findNavController
import com.purpleshade.pms.R
import com.purpleshade.pms.model.RecordList
import com.purpleshade.pms.utils.customObject.RecordDetail

/**
 * Created by pawan on 18,January,2021
 */
class PasswordViewModel(): ViewModel() {

    val passwordList: ArrayList<RecordList> = ArrayList()

    fun onEditButton(view: View){
        Log.d("====>>", "===>>>")
        for (i in 0 until passwordList.size)
        RecordDetail.recordId = passwordList[i]._id
        view.findNavController().navigate(R.id.action_homeFragment_to_updateRecordFragment)
    }
}
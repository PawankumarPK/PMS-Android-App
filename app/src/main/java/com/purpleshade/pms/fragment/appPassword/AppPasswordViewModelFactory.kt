package com.purpleshade.pms.fragment.appPassword

import android.content.Context
import android.widget.EditText
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.purpleshade.pms.db.RoomUser

/**
 * Created by pawan on 04,March,2021
 */
@Suppress("UNCHECKED_CAST")
class AppPasswordViewModelFactory(val context: Context,val editText: EditText,val user: RoomUser) :ViewModelProvider.NewInstanceFactory(){

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return AppPasswordViewModel(context,editText,user) as T
    }
}
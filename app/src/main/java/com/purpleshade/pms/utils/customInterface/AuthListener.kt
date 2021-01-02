package com.purpleshade.pms.utils.customInterface

import androidx.lifecycle.LiveData

/**
 * Created by pawan on 02,January,2021
 */
interface AuthListener {

    fun onStarted()
    fun onSuccess(responseSuccess : LiveData<String>)
    fun onFailed(message : String)
}
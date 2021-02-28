package com.purpleshade.pms.model

import androidx.lifecycle.ViewModel
import com.google.gson.annotations.SerializedName

/**
 * Created by pawan on 20,November,2020
 */
class ProfileDetails {

    @SerializedName("_id")
    var id = ""

    @SerializedName("username")
    var username = ""

    @SerializedName("email")
    var email: String? = null

}
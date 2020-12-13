package com.purpleshade.pms.network.signupModel

import com.google.gson.annotations.SerializedName

/**
 * Created by pawan on 19,November,2020
 */
class SignUpModel {
    @SerializedName("message")
    var message: String? = null

    @SerializedName("token")
    var token: String? = null
}
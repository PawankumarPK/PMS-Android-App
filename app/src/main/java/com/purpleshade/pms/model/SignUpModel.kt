package com.purpleshade.pms.model

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
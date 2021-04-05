package com.purpleshade.pms.model

import com.google.gson.annotations.SerializedName

/**
 * Created by pawan on 19,November,2020
 */
class SignUpVerificationModel {
    @SerializedName("message")
    var message: String? = null

    @SerializedName("token")
    var token: SignUpVerificationDetailModel = SignUpVerificationDetailModel()
}
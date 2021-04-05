package com.purpleshade.pms.model

import com.google.gson.annotations.SerializedName

/**
 * Created by pawan on 19,November,2020
 */
class SignUpVerificationDetailModel {
    @SerializedName("isVerified")
    var isVerified: Boolean? = null

    @SerializedName("signUpToken")
    var signUpToken: String? = null
}
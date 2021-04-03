package com.purpleshade.pms.model

import com.google.gson.annotations.SerializedName

/**
 * Created by pawan on 19,November,2020
 */
class UserVerificationDetailModel {
    @SerializedName("isVerified")
    var isVerified: Boolean? = null

    @SerializedName("forgotPassToken")
    var forgotPassToken: String? = null
}
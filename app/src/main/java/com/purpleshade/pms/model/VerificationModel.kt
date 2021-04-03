package com.purpleshade.pms.model

import com.google.gson.annotations.SerializedName

/**
 * Created by pawan on 19,November,2020
 */
class VerificationModel {
    @SerializedName("message")
    var message: String? = null

    @SerializedName("user")
    var user: UserVerificationDetailModel? = null
}
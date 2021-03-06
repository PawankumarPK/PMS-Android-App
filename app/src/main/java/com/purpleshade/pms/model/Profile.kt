package com.purpleshade.pms.model

import com.google.gson.annotations.SerializedName
import com.purpleshade.pms.model.RecordList

/**
 * Created by pawan on 20,November,2020
 */
class Profile {

    @SerializedName("message")
    var message: String? = null

    @SerializedName("data")
    var data: ArrayList<ProfileDetails> = ArrayList()

}
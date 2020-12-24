package com.purpleshade.pms.utils

import com.google.gson.annotations.SerializedName

/**
 * Created by pawan on 20,November,2020
 */
class Records {

    @SerializedName("message")
    var message: String? = null

    @SerializedName("record")
    var recordDetail: ArrayList<RecordList> = ArrayList()

}
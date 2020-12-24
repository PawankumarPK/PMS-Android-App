package com.purpleshade.pms.utils

import com.google.gson.annotations.SerializedName

/**
 * Created by pawan on 20,November,2020
 */
class RecordList {

    @SerializedName("_id")
    var _id = ""

    @SerializedName("title")
    var title = ""

    @SerializedName("websiteAddress")
    var websiteAddress: String? = null

    @SerializedName("email")
    var email = ""

    @SerializedName("password")
    var password = ""

    @SerializedName("addNote")
    var addNote = ""

    @SerializedName("date")
    var date = ""

}
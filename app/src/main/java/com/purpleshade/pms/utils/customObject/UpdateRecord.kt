package com.purpleshade.pms.utils.customObject

import com.google.gson.annotations.SerializedName


/**
 * Created by pawan on 24,December,2020
 */
class UpdateRecord(title: String, webAddress: String, email: String, password: String, addNote: String) {
    private val title: String? = title
    private val webAddress: String? = webAddress
    private val email: String? = email
    private val password: String? = password
    private val addNote: String? = addNote
}
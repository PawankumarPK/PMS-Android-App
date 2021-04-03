package com.purpleshade.pms.model

import com.google.gson.annotations.SerializedName


/**
 * Created by pawan on 24,December,2020
 */
class UpdateRecord(title: String, websiteAddress: String, email: String, password: String, addNote: String) {
    private val title: String? = title
    private val websiteAddress: String? = websiteAddress
    private val email: String? = email
    private val password: String? = password
    private val addNote: String? = addNote
}
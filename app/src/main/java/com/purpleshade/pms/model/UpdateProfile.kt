package com.purpleshade.pms.model

import com.google.gson.annotations.SerializedName


/**
 * Created by pawan on 24,December,2020
 */
class UpdateProfile(username: String, email: String) {
    private val username: String? = username
    private val email: String? = email
}
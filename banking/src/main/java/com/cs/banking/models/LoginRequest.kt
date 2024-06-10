package com.cs.banking.models

import com.google.gson.annotations.SerializedName


data class LoginRequest(
    @SerializedName("CorporateId") var corporateId: String? = null,
    @SerializedName("Password") var password: String? = null,
    @SerializedName("UserId") var userId: String? = null,
)
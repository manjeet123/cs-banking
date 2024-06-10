package com.cs.banking.models

import android.os.Parcel
import android.os.Parcelable
import androidx.versionedparcelable.ParcelField
import com.google.gson.annotations.SerializedName


data class LoginResponse(
    @SerializedName("Token") var token: String? = null,
    @SerializedName("MessageId") var messageId: String? = null,
    @SerializedName("RequestId") var requestId: String? = null,
    @SerializedName("StatusCode") var statusCode: String? = null,
    @SerializedName("StatusDescription") var statusDescription: String? = null,
    @SerializedName("Timestamp") var timestamp: String? = null
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(token)
        parcel.writeString(messageId)
        parcel.writeString(requestId)
        parcel.writeString(statusCode)
        parcel.writeString(statusDescription)
        parcel.writeString(timestamp)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<LoginResponse> {
        override fun createFromParcel(parcel: Parcel): LoginResponse {
            return LoginResponse(parcel)
        }

        override fun newArray(size: Int): Array<LoginResponse?> {
            return arrayOfNulls(size)
        }
    }
}
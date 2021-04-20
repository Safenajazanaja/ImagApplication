package com.example.imagapplication

import com.google.gson.annotations.SerializedName

data class UploadImageResponse(
    @SerializedName("response") val response: String? = null
)

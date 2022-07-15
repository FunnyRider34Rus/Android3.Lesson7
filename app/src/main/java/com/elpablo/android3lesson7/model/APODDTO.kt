package com.elpablo.android3lesson7.model

import com.google.gson.annotations.SerializedName

data class APODDTO(
    val date: String,
    val explanation: String,
    val hdurl: String,
    @SerializedName("media_type")
    val mediaType: String,
    @SerializedName("service_version")
    val serviceVersion: String,
    val title: String,
    val url: String
)

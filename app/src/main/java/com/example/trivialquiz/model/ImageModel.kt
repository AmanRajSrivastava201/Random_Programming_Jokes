package com.example.trivialquiz.model

import com.google.gson.annotations.SerializedName

data class ImageModel(
    @SerializedName("url")
    val url: String
)
package com.application.lab1

import com.google.gson.annotations.SerializedName

class SocialData(
    @SerializedName("social_id") var social_id: String,
    @SerializedName("title") var title: String,
    @SerializedName("url") var url: String,
    @SerializedName("icon") var icon: String
) {
}
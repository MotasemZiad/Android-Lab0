package com.application.lab1

import com.google.gson.annotations.SerializedName

class Social(@SerializedName("records") var list: ArrayList<SocialData>) {
}
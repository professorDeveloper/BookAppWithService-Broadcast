package com.azamovhudstc.bookappwithcache.data.remote.response.book


import com.google.gson.annotations.SerializedName

data class GetSocialUserResponseItem(
    @SerializedName("firstName")
    val firstName: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("lastName")
    val lastName: String
)
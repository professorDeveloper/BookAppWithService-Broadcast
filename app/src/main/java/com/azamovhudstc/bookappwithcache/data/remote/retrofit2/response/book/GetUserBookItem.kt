package com.azamovhudstc.bookappwithcache.data.remote.response.book


import com.google.gson.annotations.SerializedName

data class GetUserBookItem(
    @SerializedName("author")
    val author: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("disLikeCount")
    val disLikeCount: Int,
    @SerializedName("fav")
    val fav: Boolean,
    @SerializedName("id")
    val id: Int,
    @SerializedName("isLike")
    val isLike: Boolean?,
    @SerializedName("likeCount")
    val likeCount: Int,
    @SerializedName("pageCount")
    val pageCount: Int,
    @SerializedName("title")
    val title: String
)
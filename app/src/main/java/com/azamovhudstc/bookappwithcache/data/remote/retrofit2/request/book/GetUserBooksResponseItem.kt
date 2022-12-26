package com.azamovhudstc.bookappwithcache.data.remote.request.book


import java.io.Serializable

data class GetUserBooksResponseItem(
    val author: String,
    val description: String,
    val disLikeCount: Int,
    val fav: Boolean,
    val id: Int,
    val isLike: Boolean,
    val likeCount: Int,
    val pageCount: Int,
    val title: String
) : Serializable {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as GetUserBooksResponseItem

        if (author != other.author) return false
        if (description != other.description) return false
        if (disLikeCount != other.disLikeCount) return false
        if (fav != other.fav) return false
        if (id != other.id) return false
        if (isLike != other.isLike) return false
        if (likeCount != other.likeCount) return false
        if (pageCount != other.pageCount) return false
        if (title != other.title) return false

        return true
    }

    override fun hashCode(): Int {
        var result = author.hashCode()
        result = 31 * result + description.hashCode()
        result = 31 * result + disLikeCount
        result = 31 * result + fav.hashCode()
        result = 31 * result + id
        result = 31 * result + isLike.hashCode()
        result = 31 * result + likeCount
        result = 31 * result + pageCount
        result = 31 * result + title.hashCode()
        return result
    }
}

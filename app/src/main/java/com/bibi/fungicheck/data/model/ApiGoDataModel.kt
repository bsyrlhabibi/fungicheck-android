package com.bibi.fungicheck.data.model

import com.google.gson.annotations.SerializedName
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Article(
    @SerializedName("id")
    val id: Int,
    @SerializedName("title")
    val title: String,
    @SerializedName("photo")
    val photo: String,
    @SerializedName("content")
    val content: String,
    @SerializedName("author")
    val author: String,
    @SerializedName("date")
    val date: String
) : Parcelable

data class Pagination(
    val current_page: Int,
    val total_items: Int,
    val total_pages: Int,
    val prev_page: Int?,
    val next_page: Int?
)
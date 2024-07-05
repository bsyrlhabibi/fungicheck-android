package com.bibi.fungicheck.data.response

import com.bibi.fungicheck.data.model.Article
import com.bibi.fungicheck.data.model.Pagination

data class ArticleResponse(
    val message: String,
    val data: List<Article>,
    val pagination: Pagination
)
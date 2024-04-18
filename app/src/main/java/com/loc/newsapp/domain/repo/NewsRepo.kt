package com.loc.newsapp.domain.repo

import androidx.paging.PagingData
import com.loc.newsapp.domain.model.Article
import kotlinx.coroutines.flow.Flow

interface NewsRepo {
    fun getNews(sources:List<String>):Flow<PagingData<Article>>
}
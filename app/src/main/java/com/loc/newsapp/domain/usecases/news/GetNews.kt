package com.loc.newsapp.domain.usecases.news

import androidx.paging.PagingData
import com.loc.newsapp.domain.model.Article
import com.loc.newsapp.domain.repo.NewsRepo
import kotlinx.coroutines.flow.Flow

class GetNews(
    private val newsRepo: NewsRepo,
) {
    operator fun invoke(sources:List<String>):Flow<PagingData<Article>>{
        return newsRepo.getNews(sources)
    }
}
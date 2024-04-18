package com.loc.newsapp.data.repo

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.loc.newsapp.data.remote.NewsApi
import com.loc.newsapp.data.remote.NewsPagingSource
import com.loc.newsapp.domain.model.Article
import com.loc.newsapp.domain.repo.NewsRepo
import kotlinx.coroutines.flow.Flow

class NewsRepoImpl(
    private val newsApi: NewsApi,
) : NewsRepo {
    override fun getNews(sources: List<String>): Flow<PagingData<Article>> {
        return Pager(config = PagingConfig(pageSize = 10), pagingSourceFactory ={
            NewsPagingSource(
                newsApi = newsApi,
                sources = sources.joinToString(separator = ","),
            )
        }).flow
    }
}
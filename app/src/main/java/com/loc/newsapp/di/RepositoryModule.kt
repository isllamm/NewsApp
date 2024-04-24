package com.loc.newsapp.di
import com.loc.newsapp.data.repo.NewsRepoImpl
import com.loc.newsapp.domain.repo.NewsRepo
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindNewsRepository(newsRepositoryImpl: NewsRepoImpl): NewsRepo

}
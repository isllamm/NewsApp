package com.loc.newsapp.di

import android.app.Application
import androidx.room.Room
import com.loc.newsapp.data.local.NewsDao
import com.loc.newsapp.data.local.NewsDatabase
import com.loc.newsapp.data.local.NewsTypeConverter
import com.loc.newsapp.data.manger.LocalUserMangerImpl
import com.loc.newsapp.data.remote.NewsApi
import com.loc.newsapp.data.repo.NewsRepoImpl
import com.loc.newsapp.domain.manger.LocalUserManger
import com.loc.newsapp.domain.repo.NewsRepo
import com.loc.newsapp.domain.usecases.appEntry.AppEntryUseCases
import com.loc.newsapp.domain.usecases.appEntry.ReadAppEntry
import com.loc.newsapp.domain.usecases.appEntry.SaveAppEntry
import com.loc.newsapp.domain.usecases.news.GetNews
import com.loc.newsapp.domain.usecases.news.NewsUseCases
import com.loc.newsapp.domain.usecases.news.SearchNews
import com.loc.newsapp.util.Constants
import com.loc.newsapp.util.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideLocalUserManger(
        application: Application
    ): LocalUserManger = LocalUserMangerImpl(application)

    @Provides
    @Singleton
    fun provideAppEntryUseCases(
        localUserManger: LocalUserManger
    ) = AppEntryUseCases(
        readAppEntry = ReadAppEntry(localUserManger),
        saveAppEntry = SaveAppEntry(localUserManger)
    )
    @Provides
    @Singleton
    fun provideNewsApi():NewsApi{
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NewsApi::class.java)
    }
    @Provides
    @Singleton
    fun provideNewsRepo(
        newsApi: NewsApi
    ):NewsRepo= NewsRepoImpl(newsApi)
    @Provides
    @Singleton
    fun provideNewsUseCases(
        newsRepo: NewsRepo
    ):NewsUseCases{
        return NewsUseCases(
            getNews = GetNews(newsRepo),
            searchNews = SearchNews(newsRepo)
        )
    }
    @Provides
    @Singleton
    fun provideNewsDatabase(
        application: Application
    ):NewsDatabase{
        return Room.databaseBuilder(
            context = application,
            klass = NewsDatabase::class.java,
            name = Constants.NEWS_DB,
        ).addTypeConverter(NewsTypeConverter())
            .fallbackToDestructiveMigration()
            .build()
    }
    @Provides
    @Singleton
    fun provideNewsDao(
        newsDatabase: NewsDatabase
    ):NewsDao = newsDatabase.newsDao
}
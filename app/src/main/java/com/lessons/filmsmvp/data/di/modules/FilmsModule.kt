package com.lessons.filmsmvp.data.di.modules

import com.lessons.filmsmvp.data.film.FilmsRepository
import com.lessons.filmsmvp.data.film.FilmsRepositoryImpl
import com.lessons.filmsmvp.data.film.datasource.CacheFilmsDataSource
import com.lessons.filmsmvp.data.film.datasource.CacheFilmsDataSourceImpl
import com.lessons.filmsmvp.data.film.datasource.CloudFilmsDataSource
import com.lessons.filmsmvp.data.film.datasource.FilmsDataSource
import com.lessons.filmsmvp.data.network.AndroidNetworkStatus
import com.lessons.filmsmvp.data.network.INetworkStatus
import com.lessons.filmsmvp.presentation.MainActivity
import com.lessons.filmsmvp.presentation.filminfo.FilmInfoFragment
import com.lessons.filmsmvp.presentation.filmlist.FilmsFragment
import dagger.Binds
import dagger.Module
import dagger.Reusable
import dagger.android.ContributesAndroidInjector
import javax.inject.Singleton

@Module(
    includes = [DatabaseModule::class]
)

interface FilmsModule {

    @ContributesAndroidInjector
    fun bindMainActivity(): MainActivity

    @ContributesAndroidInjector
    fun bindFilmsFragment(): FilmsFragment

    @ContributesAndroidInjector
    fun bindFilmInfoFragment(): FilmInfoFragment

    @Reusable
    @Binds
    fun bindFilmsRepository(repository: FilmsRepositoryImpl): FilmsRepository

    @Reusable
    @Binds
    fun bindFilmsDataSource(dataSource: CloudFilmsDataSource): FilmsDataSource

    @Reusable
    @Binds
    fun bindCacheFilmsDataSource(dataSource: CacheFilmsDataSourceImpl): CacheFilmsDataSource

    @Singleton
    @Binds
    fun bindAndroidNetworkStatus(androidNetworkStatus: AndroidNetworkStatus): INetworkStatus
}
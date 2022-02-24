package com.arya.moviecatalogue.di

import android.app.Application
import androidx.room.Room
import com.arya.moviecatalogue.data.source.MovieRepository
import com.arya.moviecatalogue.data.source.local.LocalDataSource
import com.arya.moviecatalogue.data.source.local.room.MovieDao
import com.arya.moviecatalogue.data.source.local.room.MovieDatabase
import com.arya.moviecatalogue.data.source.remote.RemoteDataSource
import com.arya.moviecatalogue.network.NetworkClient
import com.arya.moviecatalogue.ui.detail.DetailViewModel
import com.arya.moviecatalogue.ui.favorite.FavoriteItemViewModel
import com.arya.moviecatalogue.ui.movie.MovieViewModel
import com.arya.moviecatalogue.ui.tv.TvViewModel
import com.arya.moviecatalogue.utils.AppExecutors
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    single { NetworkClient.getApiService() }
    single { RemoteDataSource() }
    single { LocalDataSource(get()) }
    single { AppExecutors() }

}

val databaseModule = module {

    fun provideDatabase(application: Application): MovieDatabase {
        return Room.databaseBuilder(
            application.applicationContext,
            MovieDatabase::class.java,
            "Movies.db"
        ).fallbackToDestructiveMigration().build()
    }

    fun provideMovieDao(database: MovieDatabase): MovieDao {
        return database.movieDao()
    }

    single { provideDatabase(androidApplication()) }
    single { provideMovieDao(get()) }

}

val repositoryModule = module {

    single { MovieRepository(get(), get(), get()) }

}

val viewModelModule = module {

    viewModel { MovieViewModel(get()) }
    viewModel { TvViewModel(get()) }
    viewModel { DetailViewModel(get()) }
    viewModel { FavoriteItemViewModel(get()) }

}
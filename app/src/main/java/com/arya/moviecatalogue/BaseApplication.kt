package com.arya.moviecatalogue

import android.app.Application
import com.arya.moviecatalogue.di.appModule
import com.arya.moviecatalogue.di.databaseModule
import com.arya.moviecatalogue.di.repositoryModule
import com.arya.moviecatalogue.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class BaseApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(if (BuildConfig.DEBUG) Level.ERROR else Level.NONE)
            androidContext(this@BaseApplication)
            modules(
                listOf(
                    appModule,
                    databaseModule,
                    repositoryModule,
                    viewModelModule
                )
            )
        }
    }

}
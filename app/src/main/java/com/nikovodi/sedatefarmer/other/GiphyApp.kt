package com.nikovodi.sedatefarmer.other

import android.app.Application
import com.nikovodi.sedatefarmer.di.dataModule
import com.nikovodi.sedatefarmer.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class GiphyApp : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@GiphyApp)
            modules(listOf(dataModule, viewModelModule))
        }
    }
}
package com.nikovodi.sedatefarmer.di

import com.nikovodi.sedatefarmer.data.GiphyApi
import com.nikovodi.sedatefarmer.other.API_KEY
import com.nikovodi.sedatefarmer.other.BASE_URL
import com.nikovodi.sedatefarmer.viewmodel.GifListViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel

import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

val dataModule = module {
    single<GiphyApi> {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        val okHttpClient = OkHttpClient.Builder().addInterceptor {
            val originalRequest = it.request()
            val newHttpUrl =
                originalRequest.url.newBuilder().addQueryParameter("api_key", API_KEY).build()
            val newRequest = originalRequest.newBuilder().url(newHttpUrl).build()
            it.proceed(newRequest)
        }.addInterceptor(httpLoggingInterceptor).build()

        val retrofit = Retrofit.Builder().baseUrl(BASE_URL).client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create()).build()
        retrofit.create(GiphyApi::class.java)
    }
}

val viewModelModule = module {
    viewModel { GifListViewModel(get()) }
}
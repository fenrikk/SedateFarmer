package com.nikovodi.sedatefarmer.data

import com.nikovodi.sedatefarmer.data.model.GiphyResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface GiphyApi {
    @GET("./v1/gifs/trending")
    fun getTrendingGifs(
        @Query("bundle") bundle: String,
        @Query("limit") limit: Int,
        @Query("offset") offset: Int
    ): Single<GiphyResponse>
}
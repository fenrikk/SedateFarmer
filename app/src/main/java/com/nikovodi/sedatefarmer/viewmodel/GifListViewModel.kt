package com.nikovodi.sedatefarmer.viewmodel

import androidx.lifecycle.MutableLiveData
import com.nikovodi.sedatefarmer.data.GiphyApi
import com.nikovodi.sedatefarmer.data.model.GiphyData
import com.nikovodi.sedatefarmer.other.BUNDLE_SPECIFICATION
import com.nikovodi.sedatefarmer.other.FETCH_VALUE
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class GifListViewModel(
    private val giphyApi: GiphyApi
) : BaseViewModel() {

    private val giphyData: MutableLiveData<List<GiphyData>> = MutableLiveData();
    private var page = 0

    init {
        fetchGifList()
    }

    fun fetchGifList() {
        giphyApi.getTrendingGifs(BUNDLE_SPECIFICATION, FETCH_VALUE, page * FETCH_VALUE)
            .map { it.data }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                page++
                val currentData = giphyData.value ?: emptyList()
                giphyData.value = currentData + it
            }, {
                it.printStackTrace()
            }).autoDispose()
    }

    fun getGifData(): MutableLiveData<List<GiphyData>> {
        return giphyData
    }

}
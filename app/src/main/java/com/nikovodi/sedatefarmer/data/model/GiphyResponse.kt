package com.nikovodi.sedatefarmer.data.model

data class GiphyResponse(val data: List<GiphyData>)

data class GiphyData(

    val type: String,
    val id: String,
    val url: String,
    val title: String,
    val username: String,
    val rating: String,
    val images: GiphyImage,
)

data class GiphyImage(
    val fixed_height: FixedHeight
)
data class FixedHeight(
    val height: String,
    val width: String,
    val size: String,
    val url: String
)


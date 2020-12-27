package com.sileksoftware.top10podcats.model.main

import com.google.gson.annotations.SerializedName


data class FeedModel(
 @SerializedName("feed") val feed: ResultModel
)

data class ResultModel (
 @SerializedName("results") val results : List<PodcastModel>
)

data class PodcastModel(
 @SerializedName("artistName") val artistName: String,
 @SerializedName("id") val id: String,
 @SerializedName("releaseDate") val releaseDate : String,
 @SerializedName("name") val name : String,
 @SerializedName("artworkUrl100") val artworkUrl100 : String,
 @SerializedName("url") val url : String
)
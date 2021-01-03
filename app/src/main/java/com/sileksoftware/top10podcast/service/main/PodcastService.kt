package com.sileksoftware.top10podcast.service.main

import com.sileksoftware.top10podcast.model.main.FeedModel
import retrofit2.Call
import retrofit2.http.GET

interface PodcastService {
    @GET("api/v1/tr/podcasts/top-podcasts/all/10/explicit.json")
    fun getTop10Podcasts(): Call<FeedModel>
}
package com.sileksoftware.top10podcats.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sileksoftware.top10podcats.API.APIClient
import com.sileksoftware.top10podcats.model.main.FeedModel
import com.sileksoftware.top10podcats.model.main.PodcastModel
import com.sileksoftware.top10podcats.service.main.PodcastService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel : ViewModel() {
    val podcastList = MutableLiveData<List<PodcastModel>>()
    val progressView = MutableLiveData<Boolean>()
    private val podcastService = APIClient.createService(PodcastService::class.java)

    fun getTop10Podcast(){
        progressView.value = true
        podcastService.getTop10Podcasts().enqueue(object : Callback<FeedModel>{
            override fun onResponse(call: Call<FeedModel>, response: Response<FeedModel>) {
                progressView.value = false
                if (response.isSuccessful){
                    podcastList.value = response.body()?.feed?.results
                }
            }

            override fun onFailure(call: Call<FeedModel>, t: Throwable) {
                progressView.value = false
            }
        })
    }
}
package com.sileksoftware.top10podcats.API

import com.sileksoftware.top10podcats.BuildConfig
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object APIClient {
    fun <H> createService(serviceClass: Class<H>): H{
        val builder = Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(OkHttpClient())
        val retrofit = builder.build()
        return retrofit.create(serviceClass)
    }
}
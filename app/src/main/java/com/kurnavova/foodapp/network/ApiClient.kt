package com.kurnavova.foodapp.network

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

/**
 * Client for connecting to Spoonacular API using Retrofit.
 * Bear in mind there is a limited number of requests. After exceeding, all requests will return error 402.
 * API keys: a1785ee75385455dbfae0ae10841d2f4 or 6c7dbbc863cb4cd79e58f03672170c04
 */
object ApiClient {
    private const val BASE_URL = "https://api.spoonacular.com/"
    const val API_KEY = "a1785ee75385455dbfae0ae10841d2f4"

    val client: Retrofit by lazy { Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(OkHttpClient.Builder().build())
        .addConverterFactory(ScalarsConverterFactory.create())
        .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
        .build()
    }
}
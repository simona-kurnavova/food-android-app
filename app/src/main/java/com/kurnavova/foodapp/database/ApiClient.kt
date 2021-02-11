package com.kurnavova.foodapp.database

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

/**
 * Client for connecting to Spoonacular API using Retrofit.
 */
object ApiClient {

    private const val BASE_URL = "https://api.spoonacular.com/"
    const val API_KEY = "6c7dbbc863cb4cd79e58f03672170c04" // or a1785ee75385455dbfae0ae10841d2f4
    // 6c7dbbc863cb4cd79e58f03672170c04

    val client: Retrofit by lazy { Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(OkHttpClient.Builder().build())
        .addConverterFactory(ScalarsConverterFactory.create())
        .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
        .build()
    }
}
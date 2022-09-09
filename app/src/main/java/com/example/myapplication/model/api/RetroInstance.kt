package com.example.myapplication.model.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RetroInstance {
/*    var BASE_URL =
        "https://www.77577.com"*/
    var BASE_URL =
        "https://www.77577.live"

    private var retrofit: Retrofit? = null
    val retroClient: Retrofit?
        get() {
            if (retrofit == null) {
                retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }
            return retrofit
        }

    val apiService: ApiService = retroClient!!.create(ApiService::class.java)

}

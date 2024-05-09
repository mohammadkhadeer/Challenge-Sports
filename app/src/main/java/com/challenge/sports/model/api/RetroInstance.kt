package com.challenge.sports.model.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RetroInstance {
/*    var BASE_URL =
        "https://www.77577.com"*/
    //app.sports996.com
    //app.app99877.com
    //var BASE_URL = "https://app.app99877.com"
    var BASE_URL = "https://sportsapi3.com/sportsapi/"

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

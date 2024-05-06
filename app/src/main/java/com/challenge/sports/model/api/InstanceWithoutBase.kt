package com.challenge.sports.model.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object InstanceWithoutBase {
/*    var BASE_URL =
        "https://www.77577.com"*/
    //app.sports996.com
    //app.app99877.com
    var BASE_URL23232 = "https://sportsapi3.com/sportsapi/"

    private var retrofit1212: Retrofit? = null
    val retroClient2323: Retrofit?
        get() {
            if (retrofit1212 == null) {
                retrofit1212 = Retrofit.Builder()
                    .baseUrl(BASE_URL23232)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }
            return retrofit1212
        }

    val apiService2323: ApiService = retroClient2323!!.create(ApiService::class.java)

}

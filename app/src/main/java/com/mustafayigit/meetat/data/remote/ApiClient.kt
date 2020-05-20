package com.mustafayigit.meetat.data.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created By MUSTAFA
 * on 20/05/2020
 */
class ApiClient {
    companion object {
        private const val BASE_URL = ""
        fun <T> getClient(service: Class<T>): Retrofit {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
    }

}
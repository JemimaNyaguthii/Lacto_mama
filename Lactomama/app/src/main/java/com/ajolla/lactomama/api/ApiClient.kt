package com.ajolla.lactomama.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {

        var retrofit = Retrofit.Builder()
            .baseUrl("https://ajolla-backend-45e8c30af30d.herokuapp.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        fun <T> buildClient(ApiInterface: Class<T>):T{
            return retrofit.create(ApiInterface)
        }
    }

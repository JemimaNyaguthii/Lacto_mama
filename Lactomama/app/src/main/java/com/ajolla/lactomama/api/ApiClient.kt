package com.ajolla.lactomama.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

//object ApiClient {
//
//        var retrofit = Retrofit.Builder()
//            .baseUrl("https://ajolla-backend-45e8c30af30d.herokuapp.com")
//            .addConverterFactory(GsonConverterFactory.create())
//            .build()
//
//        fun <T> buildClient(ApiInterface: Class<T>):T{
//            return retrofit.create(ApiInterface)
//
//            }
//
//
class ApiClient {
    companion object {
        private const val BASE_URL = "https://ajolla-backend-45e8c30af30d.herokuapp.com"

        fun <T> buildClient(ApiInterface: Class<T>):T{
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            return retrofit.create(ApiInterface)

        }

            }
    }


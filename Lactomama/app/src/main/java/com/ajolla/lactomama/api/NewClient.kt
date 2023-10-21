package com.ajolla.lactomama.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NewClient {
        val BASE_URL = "https://ajolla-backend-45e8c30af30d.herokuapp.com"

        private var retrofit: Retrofit? = null

        fun getRetrofitClient(): Retrofit {
            if (retrofit == null) {
                retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }
            return retrofit!!
        }

}
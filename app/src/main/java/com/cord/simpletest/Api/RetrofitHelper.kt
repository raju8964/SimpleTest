package com.cord.simpletest.Api

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHelper {
    /// private const val BASE_URL = "http://jsonplaceholder.typicode.com/"
  //  private const val BASE_URL = "https://sandbox-api.coinmarketcap.com/v1/cryptocurrency/trending/"
    private const val BASE_URL="https://pro-api.coinmarketcap.com/v1/"
    fun getInstance(): Retrofit {
        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor { chain ->
            val original = chain.request()
            val request = original.newBuilder()
                .header("X-CMC_PRO_API_KEY", "2e7cedbf-d8a5-46b5-9a3c-20deb01c0fe9")
                .header("Accept", "/*/")
                .method(original.method(), original.body())
                .build()
            chain.proceed(request)
        }

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(httpClient.build())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}
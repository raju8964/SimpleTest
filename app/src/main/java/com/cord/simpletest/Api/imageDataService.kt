package com.cord.simpletest.Api

import com.cord.simpletest.ui.model.CryptoImageResponse
import com.cord.simpletest.ui.model.CryptoResponse
import retrofit2.Response
import retrofit2.http.*


interface imageDataService {
    @GET("cryptocurrency/listings/latest")
    suspend fun getData(
//        @Query("start") start: Int,
//        @Query("limit") limit: Int
    ):  Response<CryptoResponse>
    @GET("cryptocurrency/info")
     suspend fun getImage(
        @Query("id") id: Int
     ):Response<CryptoImageResponse>
}
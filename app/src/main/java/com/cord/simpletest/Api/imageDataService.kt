package com.cord.simpletest.Api

import com.cord.simpletest.models.Image
import com.cord.simpletest.ui.model.CryptoResponse
import retrofit2.Response
import retrofit2.http.*


interface imageDataService {
    @GET("cryptocurrency/listings/latest")
    suspend fun getData(
//        @Query("start") start: Int,
//        @Query("limit") limit: Int
    ):  Response<CryptoResponse>
}
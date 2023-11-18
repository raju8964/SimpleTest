package com.cord.simpletest.repo

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.cord.simpletest.Api.imageDataService
import com.cord.simpletest.models.Image
import com.cord.simpletest.ui.model.CryptoResponse
import java.lang.Exception
//// --------------------------------------------------------------------
//  this file is use for check online or offline,
//  but this time i used only for online
//  ------------------------------------------------------------------------
class MainRepo (
    private val quoteService: imageDataService,
    ) {
    private val imagesLiveData = MutableLiveData<MyResponse<CryptoResponse>>()
    val images: LiveData<MyResponse<CryptoResponse>>
        get() = imagesLiveData

    suspend fun getImage() {
        imagesLiveData.postValue(MyResponse.Loading())
        try {
            val result = quoteService.getData()
            if (result.body() != null) {
                imagesLiveData.postValue(MyResponse.Success(result.body()))
            }else{
                imagesLiveData.postValue(MyResponse.Error("API Error.."))
            }
        }
        catch (e:Exception){
            imagesLiveData.postValue(MyResponse.Error(e.message.toString()))
        }
    }
}


package com.cord.simpletest.repo

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.cord.simpletest.Api.imageDataService
import com.cord.simpletest.ui.model.CryptoImageResponse
import com.cord.simpletest.ui.model.CryptoResponse
import java.lang.Exception
//// --------------------------------------------------------------------
//  this file is use for check online or offline,
//  but this time i used only for online
//  ------------------------------------------------------------------------
class MainRepo (
    private val quoteService: imageDataService,
    ) {
    private val _cryptoLiveData = MutableLiveData<MyResponse<CryptoResponse>>()
    val crypto: LiveData<MyResponse<CryptoResponse>>
        get() = _cryptoLiveData

    private val _imagesLiveData = MutableLiveData<MyResponse<CryptoImageResponse>>()
    val images: LiveData<MyResponse<CryptoImageResponse>>
        get() = _imagesLiveData

    suspend fun getCryptos() {
        _cryptoLiveData.postValue(MyResponse.Loading())
        try {
            val result = quoteService.getData()
            if (result.body() != null) {
                _cryptoLiveData.postValue(MyResponse.Success(result.body()))
            }else{
                _cryptoLiveData.postValue(MyResponse.Error("API Error.."))
            }
        }
        catch (e:Exception){
            _cryptoLiveData.postValue(MyResponse.Error(e.message.toString()))
        }
    }

    suspend fun getImage(id:Int) {
        _imagesLiveData.postValue(MyResponse.Loading())
        try {
            val result = quoteService.getImage(id)
            if (result.body() != null) {
                _imagesLiveData.postValue(MyResponse.Success(result.body()))
            }else{
                _imagesLiveData.postValue(MyResponse.Error("API Error.."))
            }
        }
        catch (e:Exception){
            _imagesLiveData.postValue(MyResponse.Error(e.message.toString()))
        }
    }
}


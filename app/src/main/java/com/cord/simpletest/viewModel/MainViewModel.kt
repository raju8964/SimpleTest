package com.cord.simpletest.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cord.simpletest.R
import com.cord.simpletest.repo.MainRepo
import com.cord.simpletest.repo.MyResponse
import com.cord.simpletest.ui.model.CryptoImageResponse
import com.cord.simpletest.ui.model.CryptoResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

//// --------------------------------------------------------------------
//   communicate with data source and Ui
//------------------------------------------------------------------------
class MainViewModel(private val repository: MainRepo) : ViewModel() {
    ///// get cryptos
    fun postData() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getCryptos()
        }
    }

    val allCrypto: LiveData<MyResponse<CryptoResponse>>
        get() = repository.crypto

    ///// get crypto images
    fun postImages(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getImage(id)
        }
    }

    val allImage: LiveData<MyResponse<CryptoImageResponse>>
        get() = repository.images


    var txtCryptocurrency = "Cryptocurrency"
    var txtTopCryptocurre = "Top Cryptocurrency"
    var txtViewAll = "View All"
    var txtNFT = "NFT"
    var txtBitcoin = "Bitcoin"
    var txtPrice = "$55,000 USD"
    var txtTwentyFive = "+2.5%"
    var txtETH = "ETH"
    var txtPriceOne = ""

}
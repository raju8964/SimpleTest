package com.cord.simpletest.utils

import android.app.Application
import android.util.Log
import com.cord.simpletest.Api.RetrofitHelper
import com.cord.simpletest.Api.imageDataService
import com.cord.simpletest.repo.MainRepo
import com.google.firebase.auth.FirebaseAuth
import io.socket.client.IO
import io.socket.client.Socket
import io.socket.engineio.client.transports.Polling
import java.net.URISyntaxException

class ImageApplication : Application() {

    lateinit var quoteRepository: MainRepo

    override fun onCreate() {
        super.onCreate()
        initialize()
    }
    private fun initialize() {
        val quoteService = RetrofitHelper.getInstance().create(imageDataService::class.java)
        quoteRepository = MainRepo(quoteService)


    }
}

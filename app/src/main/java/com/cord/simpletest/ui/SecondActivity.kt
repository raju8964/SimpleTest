package com.cord.simpletest.ui

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import com.cord.simpletest.R
import org.greenrobot.eventbus.EventBus

class SecondActivity: AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        setContentView(R.layout.activity_second)
       /// EventBus.getDefault().register(this)
        EventBus.getDefault().postSticky(Message("Hi", ""))
    }
}
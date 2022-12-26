package com.azamovhudstc.bookappwithcache.ui.activity

import NetworkReceiver
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.net.wifi.WifiManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.azamovhudstc.bookappwithcache.R
import com.azamovhudstc.bookappwithcache.service.BookService
import com.azamovhudstc.bookappwithcache.service.recevier.MyReceiver
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        startService(Intent(this, BookService::class.java))

        // Intent Filter is useful to determine which apps wants to receive
        // which intents,since here we want to respond to change of

    }
}
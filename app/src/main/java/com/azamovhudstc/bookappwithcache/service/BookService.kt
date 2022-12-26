package com.azamovhudstc.bookappwithcache.service

import NetworkReceiver
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.Build
import android.os.IBinder
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.azamovhudstc.bookappwithcache.R
import com.azamovhudstc.bookappwithcache.data.mapping.NotificationMap
import com.azamovhudstc.bookappwithcache.repo.impl.BookRepositoryImpl
import com.azamovhudstc.bookappwithcache.service.recevier.MyReceiver
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*
import javax.inject.Inject

@AndroidEntryPoint

class BookService : Service() {

    @Inject
    lateinit var repositoryImpl: BookRepositoryImpl
    private var netwo: NetworkReceiver? = null
    override fun onBind(intent: Intent?): IBinder? = null
    lateinit var globalScope: Job
    private var count = 0
    private val receiver = MyReceiver.getInstance()

    @OptIn(DelicateCoroutinesApi::class)
    override fun onCreate() {
        super.onCreate()

        receiver.setLoadBookListener {
            createChannel(it.title.toString())
            notification(it)
        }

        netwo = NetworkReceiver.getInstance()
        registerReceiver(netwo, IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION))
        netwo!!.setOnConnectListener {
            globalScope = GlobalScope.launch(Dispatchers.Main) {

                repositoryImpl.reLoadLocalData()
            }
        }
    }

    private fun notification(bookRequest: NotificationMap) {
        val notificationBuilder = NotificationCompat.Builder(this, bookRequest.title.toString())
            .setContentText("Book App")
            .setSmallIcon(R.drawable.books)
            .setContentTitle(bookRequest.message)
            .build()
        var notificationManger = NotificationManagerCompat.from(this)
        notificationManger.notify(count, notificationBuilder)
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        return START_STICKY
    }

    private fun createChannel(id: String) {
        if (Build.VERSION.SDK_INT >= 26) {
            val channel = NotificationChannel(id, "Book App", NotificationManager.IMPORTANCE_DEFAULT)
            val notificationManager = NotificationManagerCompat.from(this)
            notificationManager.createNotificationChannel(channel)
        }
    }

    override fun onDestroy() {
        globalScope.cancel()
        super.onDestroy()
    }
}
package com.azamovhudstc.bookappwithcache.service.recevier

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.azamovhudstc.bookappwithcache.data.mapping.NotificationMap


class MyReceiver private constructor(): BroadcastReceiver() {
    companion object {
        lateinit var receiver :MyReceiver
        fun getInstance() : MyReceiver {
            if (!::receiver.isInitialized) {
                receiver = MyReceiver()
            }
            return receiver
        }
    }
    private lateinit var listener :((NotificationMap)->Unit)
    fun setLoadBookListener(f : (NotificationMap)->Unit){
        listener = f
    }
    override fun onReceive(p0: Context?, intent: Intent?) {
        if(intent != null) {
            val value = (intent.getSerializableExtra("bookRequest")) as NotificationMap
            listener.invoke(value)
        }
        /* val state = intent?.getBooleanExtra("state", false)
         listener.invoke(state!!)*/
    }
}
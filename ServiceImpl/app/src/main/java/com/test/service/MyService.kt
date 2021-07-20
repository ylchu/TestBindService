package com.test.service

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log

class MyService : Service() {

    private val binder = object : IRemoteService.Stub() {

        override fun basicTypes(
            anInt: Int,
            aLong: Long,
            aBoolean: Boolean,
            aFloat: Float,
            aDouble: Double,
            aString: String
        ) {
            // Does nothing
        }
    }

    override fun onCreate() {
        super.onCreate()
    }

    override fun onBind(intent: Intent): IBinder {
        // Return the interface
        Log.d("yuyu", "s2 onBind")
        return binder
    }
}
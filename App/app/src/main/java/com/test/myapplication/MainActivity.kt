package com.test.myapplication

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.test.service.IRemoteService


class MainActivity : AppCompatActivity() {

    private var remoteService: IRemoteService? = null
    private val resultTextView by lazy {
        this.findViewById<TextView>(R.id.bind_result)
    }

    /**
     * Class for interacting with the main interface of the service.
     */
    private val serviceConnection = object : ServiceConnection {

        override fun onServiceConnected(className: ComponentName, service: IBinder) {
            // This is called when the connection with the service has been
            // established, giving us the service object we can use to
            // interact with the service.  We are communicating with our
            // service through an IDL interface, so get a client-side
            // representation of that from the raw service object.
            remoteService = IRemoteService.Stub.asInterface(service)

            Log.e("yuyu", "Service connected")

            this@MainActivity.resultTextView.text = "Success!"
        }

        override fun onServiceDisconnected(className: ComponentName) {
            // This is called when the connection with the service has been
            // unexpectedly disconnected -- that is, its process crashed.
            remoteService = null

            Log.e("yuyu", "Service disconnected")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val intent = Intent()
        intent.component = ComponentName("com.test.service", "com.test.service.MyService")
        intent.action = IRemoteService::class.java.name
        val result = this.bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE)
        Log.e("yuyu", "Binding: $result")
    }
}
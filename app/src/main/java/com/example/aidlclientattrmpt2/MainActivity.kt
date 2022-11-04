package com.example.aidlclientattrmpt2

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {

    @SuppressLint("MissingInflatedId")
    //************************This is for testing purpose will be used in future*****************************************************
    //    companion object{
//        var str:String? =null
//    }
//    val string = str
//        lateinit var string : String
//        val textView = findViewById<TextView>(R.id.textView)

    //    private val mConnection = object : ServiceConnection {
//        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
//            iMyAidlInterface = IMyAidlInterface.Stub.asInterface(service)
//            Log.d("SHOW", "Remote Service Connected")
//        }
//
//        override fun onServiceDisconnected(name: ComponentName?) {
//            Log.d("SHOW", "Service Disconnected")
//        }
//
//    }
    //***************************************************************************************************************************

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        //This PeriodicWorkRequest obj will call the Worker class in a time span to 15 mins each and will reconnect the aidl service
        val workReq = PeriodicWorkRequestBuilder<Worker>(15, TimeUnit.MINUTES).build()

        WorkManager.getInstance(this).enqueue(workReq)

    }


}
package com.example.aidlclientattrmpt2

import android.annotation.SuppressLint
import android.content.ComponentName
import android.content.Intent
import android.content.ServiceConnection
import android.os.*
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.WorkerParameters
import com.example.aidlserverattempt2.IMyAidlInterface
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {

    @SuppressLint("MissingInflatedId")
    var iMyAidlInterface: IMyAidlInterface? = null
    lateinit var myWorker: MyWorker

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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        txt = findViewById(R.id.textView)
        val workReq = PeriodicWorkRequestBuilder<MyWorker>(15, TimeUnit.MINUTES).build()

        WorkManager.getInstance(this).enqueue(workReq)
//        txt.text = string


    }

//    override fun onResume() {
//        super.onResume()
////        val txt = findViewById<TextView>(R.id.textView)
////        val intent = Intent("AIDLService")
////        intent.setPackage("com.example.aidlserverattempt2")
////
////        bindService(intent, mConnection, BIND_AUTO_CREATE)
////        Log.d("SHOW", "bindservice called")
//
////        if(this::str.isInitialized) {
////            Log.d("SHOW2","$str")
////        }
//
////        Handler(Looper.getMainLooper()).postDelayed({
////
////            try {
////                str = iMyAidlInterface!!.color
////                Log.d("SHOW", "${str}")
////                txt.text = str
////                Log.d("SHOW", "bindservice2 called $str")
////
////
////            } catch (e: RemoteException) {
////
////            }
////        }, 30L)
//    }


    override fun onPause() {
        super.onPause()
//        unbindService(mConnection)
    }


}
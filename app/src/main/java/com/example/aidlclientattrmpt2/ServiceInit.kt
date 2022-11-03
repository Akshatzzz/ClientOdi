package com.example.aidlclientattrmpt2
//
//import android.app.Application
//import android.content.ComponentName
//import android.content.Intent
//import android.content.ServiceConnection
//import android.os.Handler
//import android.os.IBinder
//import android.os.Looper
//import android.os.RemoteException
//import android.util.Log
//import com.example.aidlserverattempt2.IMyAidlInterface
//
//class ServiceInit : Application() {
//
//
//
//    var iMyAidlInterface: IMyAidlInterface? = null
//    companion object{
//    var str:String? =null
//    }
//    val string = str
//
//
//    private val mConnection = object : ServiceConnection {
//        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
//            iMyAidlInterface = IMyAidlInterface.Stub.asInterface(service)
//            Log.d("SHOW","Remote Service Connected")
//        }
//
//        override fun onServiceDisconnected(name: ComponentName?) {
//            Log.d("SHOW","Service Disconnected")
//        }
//
//    }
//
//    override fun onCreate() {
//        super.onCreate()
//        val intent = Intent("AIDLService")
//        intent.setPackage("com.example.aidlserverattempt2")
//        bindService(intent,mConnection, BIND_AUTO_CREATE)
//        Log.d("SHOW", "bindservice called")
//
//        Handler(Looper.getMainLooper()).postDelayed({
//            try {
//                str = iMyAidlInterface!!.color
//                Log.d("SHOW","$str")
//
//
//
//            } catch (e: RemoteException) {
//
//            }
//        }, 300)
//
//    }
//
//}
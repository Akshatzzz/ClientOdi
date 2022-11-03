package com.example.aidlclientattrmpt2

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Handler
import android.os.IBinder
import android.os.Looper
import android.os.RemoteException
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.aidlserverattempt2.IMyAidlInterface
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MyWorker(context: Context, workerParameters: WorkerParameters) :
    Worker(context, workerParameters) {
    var iMyAidlInterface: IMyAidlInterface? = null
    lateinit var myDb:stringDb

    companion object {
        var str: String? = null
    }

    private val mConnection = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            iMyAidlInterface = IMyAidlInterface.Stub.asInterface(service)
            Log.d("SHOW", "Remote Service Connected")
        }

        override fun onServiceDisconnected(name: ComponentName?) {
            Log.d("SHOW", "Service Disconnected")
        }

    }

    override fun doWork(): Result {
        myDb = stringDb.getDatabase(applicationContext)
        Log.d("SHOW2", "WORKER CALLED")
        val intent = Intent("AIDLService")
        intent.setPackage("com.example.aidlserverattempt2")

        val bool =
            applicationContext.bindService(intent, mConnection, AppCompatActivity.BIND_AUTO_CREATE)
        Log.d("SHOW", "bindservice called")


        if (bool) {
            Handler(Looper.getMainLooper()).postDelayed({

                try {
                    str = iMyAidlInterface!!.color
                    Log.d("SHOW", "${str}")


                } catch (e: RemoteException) {

                }
                val myEntry = myString(0, str)
                GlobalScope.launch(Dispatchers.IO) {
                    myDb.dao().addUser(myEntry)
                }

            }, 300L)


            return Result.success()
        }
        else{
            return Result.failure()
        }

    }
}
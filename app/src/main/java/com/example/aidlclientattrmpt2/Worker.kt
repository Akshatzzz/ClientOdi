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
import com.example.aidlserverattempt2.IAidlInterface
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class Worker(context: Context, workerParameters: WorkerParameters) :
    Worker(context, workerParameters) {
    //Initialized relevant objects
    var iMyAidlInterface: IAidlInterface? = null
    lateinit var myDb:stringDb

    //the str will be set to the received string which we'll further insert to our database
    companion object {
        var str: String? = null
    }

    //The ServiceConnection object will let us decide the steps to process when service is connected
    private val mConnection = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            iMyAidlInterface = IAidlInterface.Stub.asInterface(service)
            Log.d("SHOW", "Remote Service Connected")
        }

        override fun onServiceDisconnected(name: ComponentName?) {
            Log.d("SHOW", "Service Disconnected")
        }

    }

    //This method will be called every 15 minutes, which can be changed accordingly in future
    override fun doWork(): Result {
        myDb = stringDb.getDatabase(applicationContext)
        Log.d("SHOW2", "WORKER CALLED")

        //this intent should have same package as that of the aidl interface in the service application
        val intent = Intent("AIDLService")
        intent.setPackage("com.example.aidlserverattempt2")

        //this will be true if the service is connected else will be false
        val bool =
            applicationContext.bindService(intent, mConnection, AppCompatActivity.BIND_AUTO_CREATE)
        Log.d("SHOW", "bindservice called")


        if (bool) {
            //delay of 300ms is provided cause it takes sometime for the service to get connected
            Handler(Looper.getMainLooper()).postDelayed({

                try {
                    // sendStr() will return the sent string from the service app
                   str = iMyAidlInterface!!.sendStr()
                    Log.d("SHOW", "${str}")


                } catch (e: RemoteException) {

                }
                val Entry = StringDataClass(0, str)
                GlobalScope.launch(Dispatchers.IO) {
                    myDb.dao().insert(Entry)
                }

            }, 300L)


            return Result.success()
        }
        else{
            //if service doesn't get connected this will be returned
            return Result.failure()
        }

    }
}
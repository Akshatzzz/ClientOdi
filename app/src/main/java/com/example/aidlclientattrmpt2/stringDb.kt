package com.example.aidlclientattrmpt2

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

//Our DB is singleton so that one single instance is created throught the project
@Database(entities = [StringDataClass::class], version = 1)
abstract class stringDb : RoomDatabase() {

    abstract fun dao():strDao

    companion object{
        @Volatile
        private var INSTANCE: stringDb ?= null

        fun getDatabase(context: Context):stringDb =
            INSTANCE?: synchronized(this){
                INSTANCE?: buildBatabase(context).also {
                    INSTANCE = it
                }
            }
        fun buildBatabase(context: Context) =
            Room.databaseBuilder(context,
                stringDb::class.java,
                "face_user_database").build()
    }

}
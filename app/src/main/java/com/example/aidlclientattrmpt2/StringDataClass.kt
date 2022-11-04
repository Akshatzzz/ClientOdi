package com.example.aidlclientattrmpt2

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "test_table")
data class StringDataClass(
 @PrimaryKey(autoGenerate = true)
    val id:Int,
 // reveived Str is a string that we receive and insert it into the database
    val receivedStr:String?
)

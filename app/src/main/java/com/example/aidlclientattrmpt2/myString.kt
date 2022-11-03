package com.example.aidlclientattrmpt2

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "test_table")
data class myString(
 @PrimaryKey(autoGenerate = true)
    val id:Int,
    val mystr:String?
)

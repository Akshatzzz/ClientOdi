package com.example.aidlclientattrmpt2

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface strDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addUser(mystr:myString)

    @Query("SELECT * from test_table")
    suspend fun select():List<myString>
}
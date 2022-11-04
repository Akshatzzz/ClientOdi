package com.example.aidlclientattrmpt2

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface strDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(str:StringDataClass)

    @Query("SELECT * from test_table")
    suspend fun select():List<StringDataClass>
}
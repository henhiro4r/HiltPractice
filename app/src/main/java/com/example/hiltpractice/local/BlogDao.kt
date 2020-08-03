package com.example.hiltpractice.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface BlogDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(blogCacheEntity: BlogCacheEntity): Long //long return the row number the data inserted or -1 if error

    @Query("SELECT * FROM blogs")
    suspend fun get(): List<BlogCacheEntity>
}
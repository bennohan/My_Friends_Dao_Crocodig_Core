package com.example.myfriendsroomdatabase.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface FriendDao {
    @Insert
    fun insert(friend: Friend)

    @Query("SELECT * FROM Friend")
    fun getAll(): LiveData<List<Friend>>

    @Update
    fun update(friend: Friend)

    @Delete
    fun delete(friend: Friend)

}
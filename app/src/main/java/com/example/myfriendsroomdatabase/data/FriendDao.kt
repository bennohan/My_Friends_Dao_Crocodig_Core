package com.example.myfriendsroomdatabase.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.crocodic.core.data.CoreDao

@Dao
abstract class FriendDao:CoreDao <Friend>{
    @Query("SELECT * FROM Friend")
    abstract fun getAll():LiveData<List<Friend>>
}
//interface FriendDao {
//    @Insert
//    fun insert(friend: Friend)
//
//    @Query("SELECT * FROM Friend")
//    fun getAll(): LiveData<List<Friend>>
//
//    @Update
//    fun update(friend: Friend)
//
//    @Delete
//    fun delete(friend: Friend)

//}
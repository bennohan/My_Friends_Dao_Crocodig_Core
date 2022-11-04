package com.example.myfriendsroomdatabase

import androidx.lifecycle.LiveData
import com.example.myfriendsroomdatabase.data.Friend
import com.example.myfriendsroomdatabase.data.FriendDao

class FriendRepository(private val friendDao: FriendDao) {
    fun getAll(): LiveData<List<Friend>>{
        return friendDao.getAll()
    }
}
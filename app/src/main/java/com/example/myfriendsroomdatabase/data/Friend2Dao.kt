package com.example.myfriendsroomdatabase.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.crocodic.core.data.CoreDao


@Dao
abstract class Friend2Dao :CoreDao<Friend>{
    @Query("SELECT * FROM Friend")
    abstract fun getAll():LiveData<List<Friend>>
}
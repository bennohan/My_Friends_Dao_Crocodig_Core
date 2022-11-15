package com.example.myfriendsroomdatabase.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Friend (
    val name: String,
    val school: String,
    val hobby: String,
   var phoneNumber: String
    ){
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}

package com.example.myfriendsroomdatabase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import com.example.myfriendsroomdatabase.data.Friend
import com.example.myfriendsroomdatabase.data.MyDatabase
import com.example.myfriendsroomdatabase.databinding.ActivityAddFriendBinding
import com.example.myfriendsroomdatabase.databinding.ActivityMainBinding
import kotlinx.coroutines.launch
import java.util.concurrent.Executors

class AddFriend : AppCompatActivity() {

    private lateinit var binding: ActivityAddFriendBinding

    var name =""
    var school =""
    var hobby= ""
    var phoneNumber= ""

    private lateinit var myDatabase: MyDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_add_friend)
        binding.activity = this
        myDatabase = MyDatabase.getDatabase(this)
    }

    fun save(){
        if(name.isNotEmpty() &&  school.isNotEmpty() && hobby.isNotEmpty()   ) {
            val newFriend = Friend(name, school, hobby,phoneNumber  )

            //Executors.newSingleThreadExecutor().execute {
                //myDatabase.friendDao().insert(newFriend)
            //}

            lifecycleScope.launch {
                myDatabase.friendDao().insert(newFriend)

            }

            Toast.makeText(this, "Data berhasil di simpan", Toast.LENGTH_SHORT).show()
            finish()
        }else{
                Toast.makeText(this, "Data belum lengkap", Toast.LENGTH_SHORT).show()
            }
        }
    }


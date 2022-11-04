package com.example.myfriendsroomdatabase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.myfriendsroomdatabase.data.MyDatabase
import com.example.myfriendsroomdatabase.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private lateinit var myDatabase: MyDatabase
    private lateinit var repository: FriendRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        binding.activity = this
        myDatabase = MyDatabase.getDatabase(this)
        repository = FriendRepository(myDatabase.friendDao())


        repository.getAll().observe(this){
            binding.rvFriend.adapter = FriendAdapter(it)
        }
        //myDatabase.friendDao().getAll().observe(this){
            //binding.rvFriend.adapter = FriendAdapter(it)
        //}
    }

    fun addFriend(){
        startActivity(Intent(this,AddFriend::class.java))
    }

}
package com.example.myfriendsroomdatabase.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(
    entities = [Friend::class],
    version = 2,
    exportSchema = false
)
abstract class MyDatabase : RoomDatabase() {
    abstract fun friendDao(): FriendDao

    companion object {
        // Singleton prevents multiple instances of database opening at the same time.
        @Volatile
        private var INSTANCE: MyDatabase? = null

        fun getDatabase(context: Context): MyDatabase{
            val tempInstance = INSTANCE
            if (tempInstance !=null){
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MyDatabase ::class.java,
                    "data_teman"
                )
                   .addMigrations(MIGRATION_1_2)
                    //.fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                return instance
            }
        }

        /*ADD COLUMN*/

        val MIGRATION_1_2 = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("ALTER TABLE Friend ADD COLUMN phone_number TEXT NOT NULL DEFAULT ''")
            }
        }

    }

}
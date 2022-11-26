package com.example.myfriendsroomdatabase.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(
    entities = [Friend::class],
    version = 4,
    exportSchema = false
)
abstract class MyDatabase : RoomDatabase() {
    abstract fun friendDao(): FriendDao

    companion object {
        // Singleton prevents multiple instances of database opening at the same time.
        @Volatile
        private var INSTANCE: MyDatabase? = null

        fun getDatabase(context: Context): MyDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MyDatabase::class.java,
                    "friend_database"
                )
                    .addMigrations( MIGRATION_1_2, MIGRATION_2_3, MIGRATION_3_4 )
//                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                return instance
            }
        }

        /*ADD COLUMN*/

        val MIGRATION_1_2 = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("ALTER TABLE Friend ADD COLUMN phoneNumber TEXT NOT NULL DEFAULT ''")
            }
        }

       /*RENAME COLUMN*/

       val MIGRATION_2_3 = object : Migration(2, 3) {
           override fun migrate(database: SupportSQLiteDatabase) {
               database.execSQL("ALTER TABLE Friend RENAME COLUMN phoneNumber TO phone")
           }
       }

        val MIGRATION_3_4 = object : Migration(3, 4) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("CREATE TABLE FriendBackup (id INTEGER NOT NULL, name TEXT NOT NULL, school TEXT NOT NULL, phone TEXT NOT NULL, PRIMARY KEY (id))")
                database.execSQL("INSERT INTO FriendBackup SELECT id,  name, school, phone FROM Friend")
                database.execSQL("DROP TABLE Friend")
                database.execSQL("ALTER TABLE FriendBackup RENAME to Friend")
            }
        }




    }
}
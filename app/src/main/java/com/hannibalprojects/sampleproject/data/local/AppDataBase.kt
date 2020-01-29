package com.hannibalprojects.sampleproject.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.hannibalprojects.sampleproject.data.User

@Database(entities = arrayOf(User::class), version = 1)
abstract class AppDataBase : RoomDatabase() {
    abstract fun getUsersDao(): UserDao

    companion object {
        private var Instance: AppDataBase? = null

        fun getInstance(context: Context): AppDataBase? {
            if (Instance == null) {
                synchronized(AppDataBase::class) {
                    Instance = Room.databaseBuilder(
                        context.applicationContext,
                        AppDataBase::class.java,
                        "users.db"
                    ).build()

                }
            }
            return Instance

        }

    }
}
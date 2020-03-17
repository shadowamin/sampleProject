package com.hannibalprojects.sampleproject.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.hannibalprojects.sampleproject.domain.User

@Database(entities = [User::class], version = 1)
abstract class AppDataBase : RoomDatabase() {
    abstract fun getUsersDao(): UserDao
}
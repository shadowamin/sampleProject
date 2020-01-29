package com.hannibalprojects.sampleproject.data.local

import androidx.lifecycle.LiveData
import androidx.room.*
import com.hannibalprojects.sampleproject.data.User

@Dao
interface UserDao {

    @Query("SELECT * FROM User")
    fun getAllLiveUsers() :LiveData<List<User>>

    @Query("Delete From User")
    fun deleteAllUsers()

    @Delete
    fun deleteUser(user: User)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAllUsers(users : List<User>)

}
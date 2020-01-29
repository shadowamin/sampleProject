package com.hannibalprojects.sampleproject.data

import com.hannibalprojects.sampleproject.data.local.UserDao
import com.hannibalprojects.sampleproject.data.remote.UserApi
import retrofit2.Retrofit

class UsersDataSourceImpl constructor(userApi: UserApi, userDao: UserDao) : UsersDataSource {
    override fun getUsers() {

    }

}
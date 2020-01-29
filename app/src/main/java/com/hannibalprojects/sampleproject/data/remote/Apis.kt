package com.hannibalprojects.sampleproject.data.remote

import com.hannibalprojects.sampleproject.data.User
import retrofit2.Response
import retrofit2.http.GET

interface UserApi {
    @GET("api/users")
    suspend fun getUsers(): Response<List<User>>
}
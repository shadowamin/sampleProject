package com.hannibalprojects.sampleproject.data

import com.hannibalprojects.sampleproject.data.local.LocalDataSource
import com.hannibalprojects.sampleproject.data.local.UserDao
import com.hannibalprojects.sampleproject.data.remote.RemoteDataSource
import com.hannibalprojects.sampleproject.data.remote.UserApi
import com.hannibalprojects.sampleproject.domain.User
import kotlinx.coroutines.test.runBlockingTest
import okhttp3.MediaType
import okhttp3.ResponseBody
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class UsersDataSourceImplTest {

    @Mock
    private lateinit var remoteDataSource: RemoteDataSource

    @Mock
    private lateinit var localDataSource: LocalDataSource

    private lateinit var dataSource: UsersDataSource

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    @Before
    fun prepareMocks() {
        dataSource = UsersDataSourceImpl(localDataSource, remoteDataSource)


    }

    @Test
    fun `Test if we get users from db`() {

    }

    @Test
    fun `Test if we get the right response and right result from ws`() {

    }

    private fun getMockedSuccessResponseWs(): retrofit2.Response<List<User>>{
        return  retrofit2.Response.success(200, ArrayList<User>())
    }

    private fun getMockedErrorResponseWs(): retrofit2.Response<List<User>>{
      return  retrofit2.Response.error(404,
            ResponseBody.create(MediaType.parse("application/json"), "".toByteArray()))
    }
}
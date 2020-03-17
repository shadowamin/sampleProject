package com.hannibalprojects.sampleproject.presentation.viewmodels


import androidx.lifecycle.ViewModel
import androidx.paging.DataSource
import com.hannibalprojects.sampleproject.domain.User
import com.hannibalprojects.sampleproject.domain.UsersResponse
import com.hannibalprojects.sampleproject.domain.usecases.GetUsersUseCase
import com.hannibalprojects.sampleproject.domain.usecases.RefreshUsersUseCase
import com.hannibalprojects.sampleproject.domain.usecases.UseCase
import javax.inject.Inject

class ListUsersViewModel @Inject constructor(
    private val usersUseCase: GetUsersUseCase,
    private val refreshUsersUseCase: RefreshUsersUseCase
) : ViewModel() {



    fun loadUsers(block: UseCase.Request<DataSource.Factory<Int, User>>.() -> Unit) {
        usersUseCase.execute(block)
    }

    fun refreshUsers() {
        refreshUsersUseCase.execute (myFun())
    }

    private fun myFun () : UseCase.Request<UsersResponse>.() -> Unit{
        return {
            onComplet {

            }
            onResponse {

            }
        }
    }

}
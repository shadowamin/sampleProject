package com.hannibalprojects.sampleproject.domain.usecases

import com.hannibalprojects.sampleproject.domain.UsersResponse
import kotlinx.coroutines.*
import java.lang.Exception
import kotlin.coroutines.CoroutineContext

abstract class UseCase<T> {

    private var parentJob: Job = Job()
    private var backgroundContext: CoroutineContext = Dispatchers.IO
    private var forgroundContext: CoroutineContext = Dispatchers.Main

    abstract suspend fun executeTask(): T

    fun execute(block: Response<T>.() -> Unit) {
        val response = Response<T>().apply { block() }
        unsubscribe()
        parentJob = Job()
        CoroutineScope(forgroundContext ).launch {
            try {
                val result = withContext(backgroundContext) {
                    executeTask()
                }
                response(result)
            } catch (e: Exception) {
                response(
                    UsersResponse(
                        400,
                        e.message ?: ""
                    )
                )
            }
        }
    }

    private fun unsubscribe() {
        if (parentJob != null) {
            parentJob.cancelChildren()
            parentJob.cancel()
        }
    }

    class Response<T> {
        private var onCompleted: ((T) -> Unit)? = null
        private var onResponse: ((UsersResponse) -> Unit)? = null

        fun onComplet(block: ((T) -> Unit)) {
            onCompleted = block
        }

        fun onResponse(block: ((UsersResponse) -> Unit)) {
            onResponse = block
        }

        operator fun invoke(result: T) {
            onCompleted?.invoke(result)
        }

        operator fun invoke(response: UsersResponse) {
            onResponse?.invoke(response)
        }
    }

}
package com.hannibalprojects.sampleproject.domain.usecases

import com.hannibalprojects.sampleproject.domain.UsersResponse
import kotlinx.coroutines.*
import java.lang.Exception
import kotlin.coroutines.CoroutineContext

abstract class UseCase<T> {

    private var parentJob: Job = Job()
    private var backgroundContext: CoroutineContext = Dispatchers.IO
    private var forgroundContext: CoroutineContext = Dispatchers.Main

    protected abstract suspend fun executeOnBackground(): T

    fun execute(block: Request<T>.() -> Unit) {
        val response = Request<T>().apply { block() }
        unsubscribe()
        parentJob = Job()
        CoroutineScope(forgroundContext + parentJob).launch {
            try {
                val result = withContext(backgroundContext) {
                    executeOnBackground()
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
        parentJob.apply {
            cancelChildren()
            cancel()
        }
    }

    class Request<T> {
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
package com.autught.chat.remote

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class ApiModel @Inject constructor(
    private val remote: ApiService,
    private val io: CoroutineContext
) {
    private  fun <T> flow(event: suspend () -> T): Flow<T> {
        return kotlinx.coroutines.flow.flow {
            emit(event())
        }.flowOn(io)
    }

     fun requestTest(): Flow<Nothing> {
        return flow { remote.request() }
    }

}
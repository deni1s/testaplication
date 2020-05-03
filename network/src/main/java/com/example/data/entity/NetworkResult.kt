package com.example.data.entity

import com.example.entity.Result

sealed class NetworkResult<T> {
    data class Success<T>(val value: T) : NetworkResult<T>()

    data class Failure<T>(val exception: Throwable) : NetworkResult<T>()
}

inline fun <T : Any, NewContent : Any> NetworkResult<T>.mapContent(crossinline mapper: (T) -> NewContent): Result<NewContent> {
    return when (this) {
        is NetworkResult.Success ->  Result.Success(mapper(this.value))
        is NetworkResult.Failure -> Result.Error(this.exception)
    }
}

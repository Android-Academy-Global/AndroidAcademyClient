package com.academy.android.domain


sealed class OperationResult<out S : Any?, out E : Any?> {

    data class Success<out S : Any?>(val data: S) : OperationResult<S, Nothing>()

    data class Error<out E : Any?>(val data: E) : OperationResult<Nothing, E>()
}
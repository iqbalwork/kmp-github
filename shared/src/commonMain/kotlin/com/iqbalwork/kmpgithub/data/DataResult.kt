package com.iqbalwork.kmpgithub.data

sealed interface DataResult<out T> {
    data class Success<T>(val data: T) : DataResult<T>
    data class Error(val t: Exception) : DataResult<Nothing>
}

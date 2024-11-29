package com.example.safebox.core.result

// Sealed class to represent the result state for UI data
sealed class Result<out T> {
    data object Loading : Result<Nothing>()
    data class Success<out T>(val data: T) : Result<T>()
    data object Empty : Result<Nothing>()
    data class Error(val exception: Throwable) : Result<Nothing>()
}
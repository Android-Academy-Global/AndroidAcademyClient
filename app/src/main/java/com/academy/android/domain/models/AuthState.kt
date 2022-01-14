package com.academy.android.domain.models

enum class AuthState {
    INITIAL,
    AUTHORIZED,
    GUEST,
    UNAUTHORIZED,
    LOADING,
    EXCEPTION,
}
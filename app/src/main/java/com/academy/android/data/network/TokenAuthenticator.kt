package com.academy.android.data.network

import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route
import javax.inject.Inject

// fixme: fix user auth
class TokenAuthenticator @Inject constructor(
//    private val prefsStorage: PrefsStorage
) : Authenticator {
    override fun authenticate(route: Route?, response: Response): Request? =
        if (responseCount(response) < MAX_ATTEMPTS) {
            response.request
                .newBuilder()
//                .header(AUTH_HEADER, prefsStorage.loadUser()?.token.orEmpty())
                .build()
        } else {
            null
        }

    private fun responseCount(response: Response): Int {
        var result = 1
        var r: Response? = response
        while (true) {
            r = r?.priorResponse ?: return result
            result++
        }
    }

    companion object {
        const val MAX_ATTEMPTS = 3
        const val AUTH_HEADER = "Token"
    }
}

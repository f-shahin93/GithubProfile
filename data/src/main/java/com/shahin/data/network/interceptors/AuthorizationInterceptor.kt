package com.shahin.data.network.interceptors

import com.shahin.data.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthorizationInterceptor @Inject constructor() : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
            .header("Authorization", "Bearer ${BuildConfig.TOKEN}")
            .build()
        return chain.proceed(request)
    }
}
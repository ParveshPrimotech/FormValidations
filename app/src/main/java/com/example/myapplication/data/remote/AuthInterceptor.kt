package com.example.myapplication.data.remote

import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor() : Interceptor {
    private var token = ""

    override fun intercept(chain: Interceptor.Chain): Response {
        val requestBuilder = chain.request().newBuilder()
        requestBuilder.addHeader("Authorization", token)
        return chain.proceed(requestBuilder.build())
    }
}
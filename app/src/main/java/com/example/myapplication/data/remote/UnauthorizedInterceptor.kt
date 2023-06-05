package com.example.myapplication.data.remote

import okhttp3.Interceptor
import okhttp3.Response

class UnauthorizedInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val response = chain.proceed(chain.request())
        if(response.code() == 401){

        }
        return response
    }
}
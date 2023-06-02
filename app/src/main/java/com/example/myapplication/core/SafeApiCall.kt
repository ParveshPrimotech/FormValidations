package com.example.myapplication.core

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException

class SafeApiCall {
    suspend fun <T> safeApiCall(
        apiCall: suspend () -> T
    ):NetworkResult<T>{
        return withContext(Dispatchers.IO){
            try{
                NetworkResult.Success(apiCall.invoke())
            }catch (throwable:Throwable){
                when(throwable){
                    is HttpException -> {
                        when(throwable.code()){
                            404 ->{
                                NetworkResult.Error(NetworkError.NOT_FOUND)
                            }

                            401-> {
                                NetworkResult.Error(NetworkError.UNAUTHORIZED)
                            }

                            else ->{
//                                val errorBody= throwable.response()?.errorBody()
//                                try {
//                                    val jsonObj = JSONObject(errorBody!!.charStream().readText())
//                                    NetworkResult.Error(jsonObj.getInt("status"),jsonObj.getString("message"), null)
//                                }catch (e:Exception){
//                                    NetworkResult.error(400,"Exception ${e.message}",null)
//                                }
                                NetworkResult.Error(NetworkError.UNKNOWN)
                            }
                        }
                    }
                    is IOException -> {
                        NetworkResult.Error(NetworkError.IO)
                    }
                    else ->
                        NetworkResult.Error(NetworkError.UNKNOWN)
                }
            }
        }
    }
}

sealed class NetworkResult<T>(
    val data: T? = null,
    val message: NetworkError? = null
) {

    class Success<T>(data: T) : NetworkResult<T>(data)

    class Error<T>(networkError: NetworkError?, data: T? = null) : NetworkResult<T>(data, networkError)

    class Loading<T> : NetworkResult<T>()
}

enum class NetworkError{
    NOT_FOUND,
    IO,
    UNAUTHORIZED,
    UNKNOWN
}
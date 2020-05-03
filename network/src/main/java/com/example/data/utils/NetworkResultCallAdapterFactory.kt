package com.example.data.utils

import android.util.Log
import com.example.data.entity.NetworkResult
import okhttp3.Request
import okio.Timeout
import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.Callback
import retrofit2.HttpException
import retrofit2.Response
import retrofit2.Retrofit
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

class NetworkResultCallAdapterFactory : CallAdapter.Factory() {

    override fun get(
        returnType: Type,
        annotations: Array<out Annotation>,
        retrofit: Retrofit
    ): CallAdapter<*, *>? {
        if (getRawType(returnType) != Call::class.java) return null
        val callType = getParameterUpperBound(0, returnType as ParameterizedType)
        if (getRawType(callType) != NetworkResult::class.java) return null
        val resultType = getParameterUpperBound(0, callType as ParameterizedType)
        return NetworkResultAdapter(resultType)
    }

    private class NetworkResultAdapter(private val responseType: Type) :
        CallAdapter<Type, Call<NetworkResult<Type>>> {
        override fun responseType(): Type {
            return responseType
        }

        override fun adapt(call: Call<Type>): Call<NetworkResult<Type>> {
            return NetworkResultCall(call, responseType)
        }
    }

    private class NetworkResultCall<T>(
        private val call: Call<T>,
        private val responseType: Type
    ) : Call<NetworkResult<T>> {
        override fun enqueue(callback: Callback<NetworkResult<T>>) {
            call.enqueue(object : Callback<T> {
                override fun onResponse(call: Call<T>, response: Response<T>) {
                    val result = if (response.isSuccessful) {
                        if (getRawType(responseType) == Unit::class.java) {
                            NetworkResult.Success(Unit) as NetworkResult<T>
                        } else {
                            val body = response.body()
                                ?: error("expected not-null body for not Unit response")
                            NetworkResult.Success<T>(body)
                        }
                    } else {
                        val e = HttpException(response)
                        Log.e("response error", e.message())
                        NetworkResult.Failure(e)
                    }
                    callback.onResponse(this@NetworkResultCall, Response.success(result))
                }

                override fun onFailure(call: Call<T>, t: Throwable) {
                    Log.e("response error", t.toString())
                    val result = Response.success<NetworkResult<T>>(NetworkResult.Failure(t))
                    callback.onResponse(this@NetworkResultCall, result)
                }
            })
        }

        override fun clone(): Call<NetworkResult<T>> {
            return NetworkResultCall(call.clone(), responseType)
        }

        override fun execute(): Response<NetworkResult<T>> {
            throw NotImplementedError()
        }

        override fun isExecuted(): Boolean {
            return call.isExecuted
        }

        override fun timeout(): Timeout {
            return call.timeout()
        }

        override fun isCanceled(): Boolean {
            return call.isCanceled
        }

        override fun cancel() {
            return call.cancel()
        }

        override fun request(): Request {
            return call.request()
        }
    }
}

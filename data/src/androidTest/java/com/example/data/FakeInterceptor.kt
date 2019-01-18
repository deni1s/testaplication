package com.example.data

import android.R.id.message
import android.util.Log
import okhttp3.*
import java.io.IOException


class FakeInterceptor(val stringContent : String) : Interceptor {

    private val TAG = FakeInterceptor::class.java.simpleName

    // FAKE RESPONSES.
//    private val TEACHER_ID_1 = "{\"id\":1,\"age\":28,\"name\":\"Victor Apoyan\"}"
//    private val TEACHER_ID_2 = "{\"id\":1,\"age\":16,\"name\":\"Tovmas Apoyan\"}"

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        var response: Response? = null
        if (BuildConfig.DEBUG) {
            Log.d(TAG, "---- DEBUG --- DEBUG -- DEBUG - DEBUG -- DEBUG --- DEBUG ----")
            Log.d(TAG, "----                FAKE SERVER RESPONSES                ----")
            val responseString: String
            // Get Request URI.
            val uri = chain.request().url().uri()
            Log.d(TAG, "---- Request URL: " + uri.toString())
            // Get Query String.
            val query = uri.getQuery()
            // Parse the Query String.
            val parsedQuery = query.split("=".toRegex()).dropLastWhile({ it.isEmpty() }).toTypedArray()
            responseString = stringContent

            response = Response.Builder()
                .code(200)
                .message(responseString)
                .request(chain.request())
                .protocol(Protocol.HTTP_1_0)
                .body(ResponseBody.create(MediaType.parse("application/json"), responseString.toByteArray()))
                .addHeader("content-type", "application/json")
                .build()

            Log.d(TAG, "---- DEBUG --- DEBUG -- DEBUG - DEBUG -- DEBUG --- DEBUG ----")
        } else {
            response = chain.proceed(chain.request())
        }

        return response
    }
}
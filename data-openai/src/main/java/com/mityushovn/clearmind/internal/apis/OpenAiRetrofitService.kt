package com.mityushovn.clearmind.internal.apis

import kotlinx.coroutines.flow.Flow
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import tech.thdev.network.flowcalladapterfactory.FlowCallAdapterFactory

private const val EMPTY_STRING = ""

// TODO:
private const val BASE_URL = ""

internal interface OpenAiRetrofitService {
    @GET("completion")
    fun getCompletion(@Path("text") text: String = EMPTY_STRING): Flow<String>

    /*
        factory method
     */
    companion object {
        @Volatile
        private var instance: OpenAiRetrofitService? = null
        private val lock: Any = Any()

        fun getInstance(): OpenAiRetrofitService {
            if (instance == null) {
                synchronized(lock) {
                    if (instance == null) {
                        val loggingInterceptor = HttpLoggingInterceptor()
                        val httpClient = OkHttpClient.Builder()
                            .addInterceptor(loggingInterceptor)
                            .build()

                        val retrofit = Retrofit.Builder()
                            .baseUrl(BASE_URL)
                            .client(httpClient)
                            .addConverterFactory(GsonConverterFactory.create())
                            .addCallAdapterFactory(FlowCallAdapterFactory())
                            .build()

                        instance = retrofit.create(OpenAiRetrofitService::class.java)
                    }
                }
            }
            return instance!!
        }
    }
}
package com.mityushovn.clearmind.api

import com.mityushovn.clearmind.internal.OpenAiRepositoryImpl
import com.mityushovn.clearmind.internal.apis.OpenAiRetrofitService

class OpenApiRepositoryFactory {
    companion object {
        @Volatile
        private var instance: OpenAiRepositoryApi? = null
        private var lock: Any = Any()

        fun getInstance(): OpenAiRepositoryApi {
            if (instance == null) {
                synchronized(lock) {
                    if (instance == null) {
                        instance = OpenAiRepositoryImpl(OpenAiRetrofitService.getInstance())
                    }
                }
            }
            return instance!!
        }
    }
}
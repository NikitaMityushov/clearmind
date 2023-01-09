package com.mityushovn.clearmind.internal

import com.mityushovn.clearmind.api.OpenAiRepositoryApi
import com.mityushovn.clearmind.internal.apis.OpenAiRetrofitService
import kotlinx.coroutines.flow.Flow

internal class OpenAiRepositoryImpl(
    private val api: OpenAiRetrofitService
) : OpenAiRepositoryApi {
    override fun getTextCompletion(text: String): Flow<String> = api.getTextCompletion(text)
    override fun getCodeCompletion(text: String): Flow<String> = api.getCodeCompletion(text)
    override fun getEditCompletion(text: String): Flow<String> = api.getEdit(text)
}
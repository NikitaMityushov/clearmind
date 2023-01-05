package com.mityushovn.clearmind.api

import kotlinx.coroutines.flow.Flow

interface OpenAiRepositoryApi {
    fun getTextCompletion(text: String): Flow<String>
}
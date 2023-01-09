package com.mityushovn.clearmind.api

import kotlinx.coroutines.flow.Flow

interface OpenAiRepositoryApi {
    fun getTextCompletion(text: String): Flow<String>
    fun getCodeCompletion(text: String): Flow<String>
    fun getEditCompletion(text: String): Flow<String>
}

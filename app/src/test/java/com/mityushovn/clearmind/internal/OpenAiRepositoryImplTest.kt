package com.mityushovn.clearmind.internal

import com.mityushovn.clearmind.internal.apis.OpenAiRetrofitService
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before

import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

internal class OpenAiRepositoryImplTest {

    companion object {
        private const val TEST_TEXT_REQUEST = "testRequest"
        private const val TEST_TEXT_RESPONSE = "testResponse"

        private const val TEST_CODE_REQUEST = "PYTHON3 function"
        private const val TEST_CODE_RESPONSE = "def foo:"

        private const val TEST_EDIT_REQUEST = "очепятка"
        private const val TEST_EDIT_RESPONSE = "опечатка"
    }

    private var api: OpenAiRetrofitService = mock()
    private lateinit var repository: OpenAiRepositoryImpl

    @Before
    fun setUp() {
        whenever(api.getTextCompletion(TEST_TEXT_REQUEST)).thenReturn(flow { emit(TEST_TEXT_RESPONSE) })
        whenever(api.getCodeCompletion(TEST_CODE_REQUEST)).thenReturn(flow { emit(TEST_CODE_RESPONSE) })
        whenever(api.getEdit(TEST_EDIT_REQUEST)).thenReturn(flow { emit(TEST_EDIT_RESPONSE) })
        repository = OpenAiRepositoryImpl(api)
    }

    @Test
    fun getTextCompletion() = runBlocking {
        // when
        var result = ""
        repository
            .getTextCompletion(TEST_TEXT_REQUEST)
            .collect {
                result = it
            }
        // then
        assertEquals(TEST_TEXT_RESPONSE, result)
    }

    @Test
    fun getCodeCompletion() = runBlocking {
        // when
        var result = ""
        repository
            .getCodeCompletion(TEST_CODE_REQUEST)
            .collect {
                result = it
            }
        // then
        assertEquals(TEST_CODE_RESPONSE, result)
    }

    @Test
    fun getEditCompletion() = runBlocking {
        // when
        var result = ""
        repository
            .getEditCompletion(TEST_EDIT_REQUEST)
            .collect {
                result = it
            }
        // then
        assertEquals(TEST_EDIT_RESPONSE, result)
    }
}
package com.mityushovn.clearmind.internal

import com.mityushovn.clearmind.internal.apis.OpenAiRetrofitService
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit4.MockKRule
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test

internal class OpenAiRepositoryImplTest {

    companion object {
        private const val TEST_TEXT_REQUEST = "testRequest"
        private const val TEST_TEXT_RESPONSE = "testResponse"

        private const val TEST_CODE_REQUEST = "PYTHON3 function"
        private const val TEST_CODE_RESPONSE = "def foo:"

        private const val TEST_EDIT_REQUEST = "очепятка"
        private const val TEST_EDIT_RESPONSE = "опечатка"
    }

    @get: Rule
    val rule = MockKRule(this)

    @MockK
    lateinit var api: OpenAiRetrofitService

    @InjectMockKs
    lateinit var repository: OpenAiRepositoryImpl

    @Before
    fun setUp() {
        every { api.getTextCompletion(TEST_TEXT_REQUEST) } answers { flow { emit(TEST_TEXT_RESPONSE) } }
        every { api.getCodeCompletion(TEST_CODE_REQUEST) } answers { flow { emit(TEST_CODE_RESPONSE) } }
        every { api.getEdit(TEST_EDIT_REQUEST) } answers { flow { emit(TEST_EDIT_RESPONSE) } }
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
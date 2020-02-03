package n7.myperfectemptyproject.data.source

import androidx.test.filters.MediumTest
import com.google.common.truth.Truth.assertThat
import com.nhaarman.mockitokotlin2.*
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

@MediumTest
internal class RepositoryImplTest {

    private val repositoryImpl : RepositoryImpl = mock {
        on { getNothing(any()) } doReturn null
    }

    @BeforeEach
    fun setUp() {
    }

    @AfterEach
    fun tearDown() {
    }

    @Test
    fun getSomething() {

    }

    @Test
    fun `function return null in any case`() {
        assertThat(repositoryImpl.getNothing(any())).isNull()
        // проверяет факт однократного вызова метода в течении 1000 милисекунд
        verify(repositoryImpl, after(1000)!!.times(1)).getNothing(any())

        // проверяет что ниразу не был вызван этот метод с такими параметрами
        verify(repositoryImpl, never()).getNothing(7)
        verifyNoMoreInteractions(repositoryImpl)
    }
}
package n7.myperfectemptyproject.data.source.local.model

import com.google.common.truth.Truth.assertThat
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.spy
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class LocalModelTest {


    var localModelMock: LocalModel = mock {
        on { id } doReturn 4
    }

    var localModelSpy: LocalModel = spy(LocalModel(5)) {
        on { id } doReturn 6
    }

    @BeforeEach
    fun setUp() {
    }

    @AfterEach
    fun tearDown() {
    }

    @Test
    //чтобы мокать ебаное дерьмо надо использовать интерфейсы или open классы нахуй! (а нет, можно захуярить файл и он сам пусть разберается)
    fun getId() {
        assertThat(localModelMock.id).isEqualTo(4)
        assertThat(localModelSpy.id).isEqualTo(6)
    }
}
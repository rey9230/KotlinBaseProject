package n7.myperfectemptyproject.data.source

import androidx.test.filters.MediumTest
import com.google.common.truth.Truth.assertThat
import com.nhaarman.mockitokotlin2.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import n7.myperfectemptyproject.data.source.remote.model.RemoteModel
import n7.myperfectemptyproject.data.source.remote.retrofit.UserApi
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Timeout

@ExperimentalCoroutinesApi
@MediumTest
internal class RepositoryImplTest {

    private lateinit var testDispatcher: TestCoroutineDispatcher
    private val userApi: UserApi = mock {
        onBlocking { getRandomUser() } doReturn RemoteModel(7)
    }
    private val repositoryImpl = spy(RepositoryImpl(userApi))

    @BeforeEach
    fun setUp() {
        testDispatcher = TestCoroutineDispatcher()
        Dispatchers.setMain(testDispatcher)
    }

    @AfterEach
    fun tearDown() {
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }

    @Test
    @Timeout(1)
    // todo научится ебашить такие функции чтобы запускались если создают новые ебучие корутины ( а для этого нужно им провайдить из дагера диспатчер ) чтобы тута их подменять!
    fun `function return mock object with id 7`() {
        testDispatcher.runBlockingTest {
            val something = repositoryImpl.getSomething()
            assertThat(something.id).isEqualTo(7)
            verifyBlocking(repositoryImpl, times(1)) { getSomething() }
        }
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
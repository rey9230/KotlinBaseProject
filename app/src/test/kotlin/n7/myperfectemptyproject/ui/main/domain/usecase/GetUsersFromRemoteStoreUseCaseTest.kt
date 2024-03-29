package n7.myperfectemptyproject.ui.main.domain.usecase

import androidx.test.filters.FlakyTest
import androidx.test.filters.MediumTest
import com.google.common.truth.Truth.assertThat
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import kotlinx.coroutines.ExperimentalCoroutinesApi
import n7.CoroutineTestRule
import n7.myperfectemptyproject.data.source.Repository
import n7.myperfectemptyproject.data.source.remote.model.Name
import n7.myperfectemptyproject.data.source.remote.model.RemoteUser
import n7.myperfectemptyproject.data.source.remote.model.RemoteUsers
import n7.runBlockingTest
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.BlockJUnit4ClassRunner

@ExperimentalCoroutinesApi
@MediumTest
@FlakyTest // tests that can work in 9/10 cases
@RunWith(BlockJUnit4ClassRunner::class) // default runner, we can not write it every time
class GetUsersFromRemoteStoreUseCaseTest {

    @get:Rule
    val coroutineTestRule = CoroutineTestRule()

    private val firstName = "Eugene"
    private val lastName = "Shuvagin"
    private val remoteUsers = RemoteUsers(listOf(RemoteUser(Name(firstName, lastName))))

    private val repository: Repository = mock {
        onBlocking { getRemoteUsers(any()) } doReturn remoteUsers
    }

    private val getUsersFromRemoteStoreUseCase = GetUsersFromRemoteStoreUseCase(repository, coroutineTestRule.testDispatcher)

    @Test(expected = NullPointerException::class)
    fun `throw NullPointerException`() = coroutineTestRule.runBlockingTest {
        throw NullPointerException()
    }

    @Test
    fun `verify user first and last name`() = coroutineTestRule.runBlockingTest {
        val remoteUsers = getUsersFromRemoteStoreUseCase(any())
        remoteUsers.onSuccess {
            val remoteUser = it.results[0]
            assertThat(remoteUser.name.first).isEqualTo(firstName)
            assertThat(remoteUser.name.last).isEqualTo(lastName)
        }
    }
}
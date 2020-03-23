package n7.myperfectemptyproject.ui.main.domain.usecase

import androidx.test.filters.MediumTest
import com.google.common.truth.Truth.assertThat
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import kotlinx.coroutines.ExperimentalCoroutinesApi
import n7.CoroutineTestRule
import n7.myperfectemptyproject.data.source.RepositoryImpl
import n7.myperfectemptyproject.data.source.remote.model.Name
import n7.myperfectemptyproject.data.source.remote.model.RemoteUser
import n7.myperfectemptyproject.data.source.remote.model.RemoteUsers
import n7.runBlockingTest
import org.junit.Rule
import org.junit.Test
import org.junit.jupiter.api.Timeout

@ExperimentalCoroutinesApi
@MediumTest
class GetUsersFromRemoteStoreUseCaseTest {

    @get:Rule
    val coroutineTestRule = CoroutineTestRule()

    private val firstName = "Eugene"
    private val lastName = "Shuvagin"
    private val remoteUsers = RemoteUsers(listOf(RemoteUser(Name(firstName, lastName))))

    private val repositoryImpl: RepositoryImpl = mock {
        onBlocking { getRemoteUsers(any()) } doReturn remoteUsers
    }

    private val getUsersFromRemoteStoreUseCase =
        GetUsersFromRemoteStoreUseCase(repositoryImpl, coroutineTestRule.testDispatcher)

    @Test
    @Timeout(1)
    fun `verify user first and last name`() {
        coroutineTestRule.runBlockingTest {
            val remoteUsers = getUsersFromRemoteStoreUseCase(any())
            remoteUsers.onSuccess {
                val remoteUser = it.results[0]
                assertThat(remoteUser.name.first).isEqualTo(firstName)
                assertThat(remoteUser.name.last).isEqualTo(lastName)
            }
        }
    }
}
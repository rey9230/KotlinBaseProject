package n7.myperfectemptyproject.ui.main.domain.usecase

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import n7.myperfectemptyproject.data.source.Repository
import n7.myperfectemptyproject.di.IoDispatcher
import javax.inject.Inject

class GetUsersFromRemoteStoreUseCase @Inject constructor(
    private val repository: Repository,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
) {

    suspend operator fun invoke(count: Int) = withContext(ioDispatcher) {
        delay(100)
        repository.getRemoteUsers(count)
    }
}

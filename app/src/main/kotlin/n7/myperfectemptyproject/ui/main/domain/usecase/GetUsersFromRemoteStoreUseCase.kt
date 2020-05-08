package n7.myperfectemptyproject.ui.main.domain.usecase

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import n7.myperfectemptyproject.data.source.Repository
import javax.inject.Inject

class GetUsersFromRemoteStoreUseCase @Inject constructor(
    private val repository: Repository,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) {

    suspend operator fun invoke(count: Int) = withContext(ioDispatcher) {
        kotlin.runCatching {
            delay(1000)
            repository.getRemoteUsers(count)
        }
    }
}

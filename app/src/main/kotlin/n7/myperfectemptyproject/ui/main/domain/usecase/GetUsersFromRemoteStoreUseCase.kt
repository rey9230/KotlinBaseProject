package n7.myperfectemptyproject.ui.main.domain.usecase

import javax.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import n7.myperfectemptyproject.data.source.RepositoryImpl
import n7.myperfectemptyproject.data.source.local.db.UsersDao

class GetUsersFromRemoteStoreUseCase @Inject constructor(
    private val repositoryImpl: RepositoryImpl,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) {

    suspend fun execute(count: Int) = withContext(ioDispatcher) {
        kotlin.runCatching {
            delay(3000)
            repositoryImpl.getRemoteUsers(count)
        }
    }
}

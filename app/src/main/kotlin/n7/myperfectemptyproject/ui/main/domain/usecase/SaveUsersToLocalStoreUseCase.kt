package n7.myperfectemptyproject.ui.main.domain.usecase

import javax.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import n7.myperfectemptyproject.data.source.Repository
import n7.myperfectemptyproject.data.source.local.model.LocalUser
import n7.myperfectemptyproject.data.source.remote.model.RemoteUsers
import n7.myperfectemptyproject.ui.main.domain.adapter.toLocalStore

class SaveUsersToLocalStoreUseCase @Inject constructor(
    private val repository: Repository,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) {

    suspend operator fun invoke(remoteUsers: RemoteUsers) = withContext(ioDispatcher) {
        kotlin.runCatching {
            val userList: List<LocalUser> = remoteUsers.results.map {
                it.toLocalStore()
            }
            repository.saveLocalUser(userList)
        }
    }
}

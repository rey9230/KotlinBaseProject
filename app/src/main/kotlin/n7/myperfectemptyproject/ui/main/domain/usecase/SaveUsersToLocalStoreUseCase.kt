package n7.myperfectemptyproject.ui.main.domain.usecase

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import n7.myperfectemptyproject.data.source.Repository
import n7.myperfectemptyproject.data.source.local.model.LocalUser
import n7.myperfectemptyproject.data.source.remote.model.RemoteUsers
import n7.myperfectemptyproject.di.IoDispatcher
import n7.myperfectemptyproject.ui.main.domain.adapter.toLocalStore
import javax.inject.Inject

class SaveUsersToLocalStoreUseCase @Inject constructor(
    private val repository: Repository,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
) {

    suspend operator fun invoke(remoteUsers: RemoteUsers) = withContext(ioDispatcher) {
        val userList: List<LocalUser> = remoteUsers.results.map {
            it.toLocalStore()
        }
        repository.saveLocalUser(userList)
    }
}

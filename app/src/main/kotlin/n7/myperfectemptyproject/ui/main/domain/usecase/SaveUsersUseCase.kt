package n7.myperfectemptyproject.ui.main.domain.usecase

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import n7.myperfectemptyproject.data.source.local.db.UsersDao
import n7.myperfectemptyproject.data.source.remote.model.RemoteUsers
import javax.inject.Inject

class SaveUsersUseCase @Inject constructor(
    private val userDao: UsersDao,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) {

    suspend fun execute(remoteUsers: RemoteUsers) {
        userDao.insert()
    }

}
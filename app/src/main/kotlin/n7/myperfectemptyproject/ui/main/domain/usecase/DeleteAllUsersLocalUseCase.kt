package n7.myperfectemptyproject.ui.main.domain.usecase

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import n7.myperfectemptyproject.data.source.local.db.UsersDao
import javax.inject.Inject

class DeleteAllUsersLocalUseCase @Inject constructor(
    private val userDao: UsersDao,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) {

    suspend operator fun invoke() = withContext(ioDispatcher) {
        userDao.deleteAll()
    }
}

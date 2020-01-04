package n7.myperfectemptyproject.ui.main.domain.usecase

import n7.myperfectemptyproject.data.source.local.db.AppDatabase
import n7.myperfectemptyproject.data.source.remote.RepositoryImpl
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UseCase @Inject constructor(
    private val repositoryImpl: RepositoryImpl,
    private val appDatabase: AppDatabase
) {

    suspend fun execute() = withContext(Dispatchers.IO) {
        kotlin.runCatching {
            repositoryImpl
            appDatabase
        }
    }
}

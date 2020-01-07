package n7.myperfectemptyproject.ui.main.domain.usecase

import androidx.lifecycle.LiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import n7.myperfectemptyproject.base.BaseUseCase
import n7.myperfectemptyproject.data.source.local.db.AppDatabase
import n7.myperfectemptyproject.data.source.local.model.LocalModel
import n7.myperfectemptyproject.data.source.remote.RepositoryImpl
import javax.inject.Inject

class UseCase @Inject constructor(
    private val repositoryImpl: RepositoryImpl,
    private val appDatabase: AppDatabase
) : BaseUseCase<LiveData<List<LocalModel>>, Any>() {

    override suspend fun execute(params: Any?) = withContext(Dispatchers.IO) {
        kotlin.runCatching {
            repositoryImpl
            appDatabase.myDao.getAll()
        }
    }
}

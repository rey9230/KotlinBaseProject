package n7.myperfectemptyproject.ui.main.domain.usecase

import androidx.lifecycle.LiveData
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import n7.myperfectemptyproject.base.BaseUseCase
import n7.myperfectemptyproject.data.source.RepositoryImpl
import n7.myperfectemptyproject.data.source.local.db.MyDao
import n7.myperfectemptyproject.data.source.local.model.LocalModel

class UseCase @Inject constructor(
    private val repositoryImpl: RepositoryImpl,
    private val myDao: MyDao,
    private val ioDispatcher : CoroutineDispatcher = Dispatchers.IO
) : BaseUseCase<LiveData<List<LocalModel>>, Any>() {

    override suspend fun execute(params: Any?) = withContext(ioDispatcher) {
        kotlin.runCatching {
            repositoryImpl
            myDao.getAll()
        }
    }
}

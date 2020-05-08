package n7.myperfectemptyproject.ui.main.domain.usecase

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import n7.myperfectemptyproject.data.source.Repository
import n7.myperfectemptyproject.ui.main.domain.adapter.toVo
import n7.myperfectemptyproject.ui.main.domain.vo.VOUser
import javax.inject.Inject

class GetUsersFromLocalStoreUseCase @Inject constructor(
    private val repository: Repository,
    private val ioDispatcher: CoroutineDispatcher
) {

    suspend operator fun invoke(): LiveData<List<VOUser>> = withContext(ioDispatcher) {
        Transformations.map(repository.getAllLocalUsers()) {
            it.map { user -> user.toVo() }
        }
    }
}
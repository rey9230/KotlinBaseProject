package n7.myperfectemptyproject.ui.main.domain.usecase

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import n7.myperfectemptyproject.data.source.Repository
import n7.myperfectemptyproject.di.IoDispatcher
import n7.myperfectemptyproject.ui.main.domain.adapter.toVo
import n7.myperfectemptyproject.ui.main.domain.vo.VOUser
import javax.inject.Inject

class GetUsersFromLocalStoreUseCase @Inject constructor(
    private val repository: Repository,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
) {

    operator fun invoke(): Flow<List<VOUser>> {
        return repository.getAllLocalUsers()
            .map { it.map { it.toVo() } }
            .distinctUntilChanged()
            .flowOn(ioDispatcher)
    }
}

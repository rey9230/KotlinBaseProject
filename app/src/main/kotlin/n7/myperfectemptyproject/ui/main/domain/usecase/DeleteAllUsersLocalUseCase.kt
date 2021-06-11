package n7.myperfectemptyproject.ui.main.domain.usecase

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import n7.myperfectemptyproject.data.source.Repository
import n7.myperfectemptyproject.di.IoDispatcher
import javax.inject.Inject

class DeleteAllUsersLocalUseCase @Inject constructor(
    private val repository: Repository,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
) {

    suspend operator fun invoke() = withContext(ioDispatcher) {
        repository.deleteLocalUsersAll()
    }
}

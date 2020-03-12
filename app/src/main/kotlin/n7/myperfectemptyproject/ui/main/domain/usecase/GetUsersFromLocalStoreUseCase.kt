package n7.myperfectemptyproject.ui.main.domain.usecase

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import n7.myperfectemptyproject.data.source.local.db.UsersDao
import n7.myperfectemptyproject.ui.main.domain.adapter.toVo
import n7.myperfectemptyproject.ui.main.domain.vo.UserVO
import javax.inject.Inject

class GetUsersFromLocalStoreUseCase @Inject constructor(
    private val userDao: UsersDao
) {

    suspend operator fun invoke(): LiveData<List<UserVO>> {
        return Transformations.map(userDao.getAllByLiveData()) {
            it.map { user -> user.toVo() }
        }
    }
}
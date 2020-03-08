package n7.myperfectemptyproject.ui.main.domain.usecase

import androidx.lifecycle.LiveData
import n7.myperfectemptyproject.data.source.local.db.UsersDao
import n7.myperfectemptyproject.data.source.local.model.User
import javax.inject.Inject

class GetUsersFromLocalStoreUseCase @Inject constructor(
    private val userDao: UsersDao
) {

    suspend operator fun invoke(): LiveData<List<User>> {
        return userDao.getAllByLiveData()
    }

}
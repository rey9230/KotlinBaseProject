package n7.myperfectemptyproject.data.source

import n7.myperfectemptyproject.data.source.remote.model.RemoteModel

interface Repository {
   suspend fun getSomething(): RemoteModel
}

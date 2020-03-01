package n7.myperfectemptyproject.data.source

import n7.myperfectemptyproject.data.source.remote.model.RemoteUsers

interface Repository {
    suspend fun getRemoteUsers(count : Int): RemoteUsers
}
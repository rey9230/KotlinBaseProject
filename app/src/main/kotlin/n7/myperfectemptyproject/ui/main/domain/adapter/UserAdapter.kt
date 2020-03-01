package n7.myperfectemptyproject.ui.main.domain.adapter

import n7.myperfectemptyproject.data.source.local.model.Users
import n7.myperfectemptyproject.data.source.remote.model.RemoteModel
import n7.myperfectemptyproject.data.source.remote.model.RemoteUsers

// The main task of adapters is to convert the entities used by the database
// and network clients to Domain module entities and back. This conversion has both pros and cons
// - Any data changes in one layer don’t affect other layers
// - Third-party dependencies as well as annotations required for a library don’t fall into other layers
// - There’s a possibility of multiple duplications
// - While changing the data you have to change the mapper


object UserAdapter {

    fun RemoteUsers.toLocal() {
        return Users(firstName = this.results)
    }

//    fun toLocal(remoteUsers: RemoteUsers): Users {
//        return Users()
//    }
//
//    fun toRemote(users: Users): RemoteUsers {
//        return RemoteModel()
//    }
}

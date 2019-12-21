package com.example.myperfectemptyproject.ui.main.domain.adapter

import com.example.myperfectemptyproject.data.source.local.model.LocalModel
import com.example.myperfectemptyproject.data.source.remote.model.RemoteModel

// The main task of adapters is to convert the entities used by the database
// and network clients to Domain module entities and back. This conversion has both pros and cons
// - Any data changes in one layer don’t affect other layers
// - Third-party dependencies as well as annotations required for a library don’t fall into other layers
// - There’s a possibility of multiple duplications
// - While changing the data you have to change the mapper

object ModelAdapter {

    fun toLocal(remoteModel: RemoteModel): LocalModel {
        return LocalModel(1)
    }

    fun toRemote(localModel: LocalModel): RemoteModel {
        return RemoteModel()
    }
}

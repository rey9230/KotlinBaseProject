package n7.myperfectemptyproject.data.source.remote.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class RemoteModel(
    val id :Int = 0
)

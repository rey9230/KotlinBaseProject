package n7.myperfectemptyproject.data.source.remote.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class RemoteModel(
    val id: Int = 0
)

// todo determine is Json serialization works faster without custom field name @Json
// https://github.com/square/moshi#custom-field-names-with-json
@JsonClass(generateAdapter = true) // Codegen annotation ("$ClassName + JsonAdapter") (NarutoJsonAdapter) https://github.com/square/moshi#codegen
data class RandomUsersResults(
    @Json(name = "results")
    val results: List<Result> = listOf()
)

@JsonClass(generateAdapter = true)
data class Result(
    @Json(name = "name")
    val name: Name = Name(),
    @Json(name = "picture")
    val picture: Picture = Picture()
)

@JsonClass(generateAdapter = true)
data class Name(
    @Json(name = "first")
    val first: String = "",
    @Json(name = "last")
    val last: String = "",
    @Json(name = "title")
    val title: String = ""
)

@JsonClass(generateAdapter = true)
data class Picture(
    @Json(name = "large")
    val large: String = "",
    @Json(name = "medium")
    val medium: String = "",
    @Json(name = "thumbnail")
    val thumbnail: String = ""
)

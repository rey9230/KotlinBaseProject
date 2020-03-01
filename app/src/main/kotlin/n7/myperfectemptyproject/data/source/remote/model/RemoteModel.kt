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
    @Json(name = "info")
    val info: Info = Info(),
    @Json(name = "results")
    val results: List<Result> = listOf()
)

@JsonClass(generateAdapter = true)
data class Info(
    @Json(name = "page")
    val page: Int = 0,
    @Json(name = "results")
    val results: Int = 0,
    @Json(name = "seed")
    val seed: String = "",
    @Json(name = "version")
    val version: String = ""
)

@JsonClass(generateAdapter = true)
data class Result(
    @Json(name = "name")
    val name: Name = Name(),
    @Json(name = "picture")
    val picture: Picture = Picture(),
    @Json(name = "registered")
    val registered: Registered = Registered()
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

@JsonClass(generateAdapter = true)
data class Registered(
    @Json(name = "age")
    val age: Int = 0,
    @Json(name = "date")
    val date: String = ""
)

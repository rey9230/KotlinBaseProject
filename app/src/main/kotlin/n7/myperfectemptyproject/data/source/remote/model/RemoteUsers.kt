package n7.myperfectemptyproject.data.source.remote.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.util.Date

@JsonClass(generateAdapter = true)
data class RemoteModel(
    val id: Int = 0,
    val date : Date = Date()
)

// todo determine is Json serialization works faster without custom field name @Json
// https://github.com/square/moshi#custom-field-names-with-json
@JsonClass(generateAdapter = true) // Codegen annotation ("$ClassName + JsonAdapter") (NarutoJsonAdapter) https://github.com/square/moshi#codegen
class RemoteUsers(
    @Json(name = "results")
    val results: List<Result> = listOf()
)

@JsonClass(generateAdapter = true)
class Result(
    @Json(name = "name")
    val name: Name = Name(),
    @Json(name = "picture")
    val picture: Picture = Picture(),
    @Json(name = "registered")
    val registered: Registered = Registered()
)

@JsonClass(generateAdapter = true)
class Name(
    @Json(name = "first")
    val first: String = "",
    @Json(name = "last")
    val last: String = "",
    @Json(name = "title")
    val title: String = ""
)

@JsonClass(generateAdapter = true)
class Picture(
    @Json(name = "large")
    val large: String = "",
    @Json(name = "medium")
    val medium: String = "",
    @Json(name = "thumbnail")
    val thumbnail: String = ""
)

@JsonClass(generateAdapter = true)
class Registered(
    @Json(name = "age")
    val age: Int = 0,
    @Json(name = "date")
    val date: Date = Date()
)

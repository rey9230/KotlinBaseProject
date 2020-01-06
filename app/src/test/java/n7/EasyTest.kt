package n7


import androidx.test.filters.SdkSuppress
import com.google.common.truth.Truth.assertThat
import com.google.common.truth.Truth.assertWithMessage
import com.squareup.moshi.Moshi
import n7.myperfectemptyproject.data.source.remote.model.RemoteModel
import org.junit.Test

class EasyTest {

    @Test
    fun jsonTest() {
        val jsonString = """
            {
                "id" : "1"
            }
        """.trimIndent()

        val moshi = Moshi.Builder()
//            .add(KotlinJsonAdapterFactory())
            .build()

        val result = moshi.adapter(RemoteModel::class.java).fromJson(jsonString)!!
        println(result)
        assertThat(result.id).isEqualTo(13)
    }

    @Test
    @SdkSuppress(maxSdkVersion = 28)
    fun simpleTest() {
        assertThat(true).isTrue()
        assertThat("i30mb1").contains("30")
        assertWithMessage("Without me...").that("i30mb1").contains("1")
        assertThat(listOf(1, 2, 3)).containsAnyIn(listOf(2))
    }

}
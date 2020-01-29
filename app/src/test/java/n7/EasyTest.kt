package n7

import androidx.test.filters.SdkSuppress
import com.google.common.truth.Truth.assertThat
import com.google.common.truth.Truth.assertWithMessage
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import com.squareup.moshi.Moshi
import n7.myperfectemptyproject.data.source.local.db.AppDatabase
import n7.myperfectemptyproject.data.source.remote.RepositoryImpl
import n7.myperfectemptyproject.data.source.remote.model.RemoteModel
import org.junit.Before
import org.junit.Test
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.TestInstance

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class EasyTest {

    private val repo : RepositoryImpl = mock {
        on { getString2() } doReturn "5"
    }

    @Before
    fun setUp() {
//
//        useCase = UseCase(repositoryImpl, appDatabase)
    }

//    @Nested
//    inner class InnerClass {

    //чтобы мокать ебаное дерьмо надо использовать интерфейсы или open классы нахуй!
        @Test
        fun mockTestSample() {

//            repo.getString2()
            assertWithMessage("mock failure").that(repo.getString2()).isEqualTo("5")
        }
//    }


    @Test
    fun `json testing`() {
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

    @Test
    fun measureTextSetting() {
        val startTime = System.nanoTime()


        Thread.sleep(5000)
        println((System.nanoTime() - startTime) / 1000000)
    }

}
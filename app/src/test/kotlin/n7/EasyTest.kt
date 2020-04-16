package n7

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.filters.SdkSuppress
import androidx.test.filters.SmallTest
import com.google.common.truth.Truth.assertThat
import com.google.common.truth.Truth.assertWithMessage
import com.squareup.moshi.Moshi
import com.squareup.moshi.adapters.Rfc3339DateJsonAdapter
import java.util.*
import n7.myperfectemptyproject.data.source.remote.model.RemoteModel
import org.junit.Rule
import org.junit.Test

@SmallTest
class EasyTest {

//    private val mainViewModel: MainViewModel = MainViewModel()
//    private lateinit var db: AppDatabase
//    private val context : MyApplication = ApplicationProvider.getApplicationContext()

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

//    @Before
//    fun setUp() {
//        // is it Unit test or Integration test???
//        db = Room.inMemoryDatabaseBuilder(
//            ApplicationProvider.getApplicationContext(),
//            AppDatabase::class.java
//        )
//            .allowMainThreadQueries()
//            .build()
// //
// //        useCase = UseCase(repositoryImpl, appDatabase)
//    }

//    @After
//    fun cleanUp() {
//        db.close()
//    }

//    @Test
//    fun saveTask() = testDispatcher.runBlockingTest {
//
//        // GIVEN - a new task saved in the database
//        val myDao: MyDao = db.myDao
//        val useCase = UseCase(RepositoryImpl(mock()), myDao)
// //
//        myDao.insert(LocalModel(5))
//
//        // WHEN - retrieved by id
//        val result = myDao.getById(5)
//
//        // THEN
//        assertThat(result.id).isEqualTo(5)
//    }

//    @Nested
//    inner class InnerClass {

    @Test
    fun `display json when loaded`() {
        val jsonString = """
            {
                "id" : "1",
                "date" : "2012-12-04T08:56:19.243Z"
            }
        """.trimIndent()

        val moshi = Moshi.Builder()
            .add(Date::class.java, Rfc3339DateJsonAdapter().nullSafe())
            .build()
        val result = moshi.adapter(RemoteModel::class.java).fromJson(jsonString)!!
        println(result)
        assertThat(result.id).isEqualTo(1)
    }

    @Test
    @SdkSuppress(maxSdkVersion = 28)
    fun simpleTest() {
        assertThat(true).isTrue()
        assertThat("i30mb1").contains("30")
        assertWithMessage("Without me...").that("i30mb1").contains("1")
        assertThat(listOf(1, 2, 3)).containsAnyIn(listOf(2))
    }

    @Test(timeout = 6000)
    fun measureTextSetting() {
        val startTime = System.nanoTime()
        Thread.sleep(5000)
        println((System.nanoTime() - startTime) / 1000000)
    }
}

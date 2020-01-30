package n7

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.filters.SdkSuppress
import com.google.common.truth.Truth.assertThat
import com.google.common.truth.Truth.assertWithMessage
import com.nhaarman.mockitokotlin2.mock
import com.squareup.moshi.Moshi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import n7.myperfectemptyproject.data.source.RepositoryImpl
import n7.myperfectemptyproject.data.source.local.db.AppDatabase
import n7.myperfectemptyproject.data.source.local.db.MyDao
import n7.myperfectemptyproject.data.source.local.model.LocalModel
import n7.myperfectemptyproject.data.source.remote.model.RemoteModel
import n7.myperfectemptyproject.ui.main.domain.usecase.UseCase
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.jupiter.api.TestInstance

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class EasyTest {

//    private val mainViewModel: MainViewModel = MainViewModel()

//    private val repo: RepositoryImpl = mock {
//        on { getSomething() } doReturn RemoteModel(5)
//    }

//    private lateinit var db: AppDatabase
    //    private val context : MyApplication = ApplicationProvider.getApplicationContext()
//    private lateinit var testDispatcher: TestCoroutineDispatcher

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

//    @Before
//    fun setUp() {
//        testDispatcher = TestCoroutineDispatcher()
//        Dispatchers.setMain(testDispatcher)
//
//        // is it Unit test or Integration test???
//        db = Room.inMemoryDatabaseBuilder(
//            ApplicationProvider.getApplicationContext(),
//            AppDatabase::class.java
//        )
//            .allowMainThreadQueries()
//            .build()
////
////        useCase = UseCase(repositoryImpl, appDatabase)
//    }

//    @After
//    fun cleanUp() {
//        db.close()
//
//        Dispatchers.resetMain()
//        testDispatcher.cleanupTestCoroutines()
//    }

//    @Test
//    fun saveTask() = testDispatcher.runBlockingTest {
//
//        // GIVEN - a new task saved in the database
//        val myDao: MyDao = db.myDao
//        val useCase = UseCase(RepositoryImpl(mock()), myDao)
////
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

    //чтобы мокать ебаное дерьмо надо использовать интерфейсы или open классы нахуй!
    @Test
    fun mockTestSample() {

//            repo.getString2()
//            assertWithMessage("mock failure").that(repo.getString2()).isEqualTo("5")
    }
//    }


    @Test
    fun `display json when loaded`() {
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

    @Test(timeout = 6000)
    fun measureTextSetting() {
        val startTime = System.nanoTime()


        Thread.sleep(5000)
        println((System.nanoTime() - startTime) / 1000000)
    }

}
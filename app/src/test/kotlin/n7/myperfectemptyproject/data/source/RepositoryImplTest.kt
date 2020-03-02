package n7.myperfectemptyproject.data.source

import com.nhaarman.mockitokotlin2.*

// @ExperimentalCoroutinesApi
// @MediumTest
// internal class RepositoryImplTest {
//
//     private val userApi: UserApi = mock {
//         onBlocking { getRandomUser() } doReturn RemoteModel(7)
//     }
//     private val repositoryImpl = spy(RepositoryImpl(userApi))
//
//     @get:Rule
//     val coroutineTestRule = CoroutineTestRule()
//
//     @Test
//     @Timeout(1)
//     // todo научится ебашить такие функции чтобы запускались если создают новые ебучие корутины ( а для этого нужно им провайдить из дагера диспатчер ) чтобы тута их подменять!
//     fun `function return mock object with id 7`() {
//         coroutineTestRule.runBlockingTest {
//             val something = repositoryImpl.getSomething()
//             assertThat(something.id).isEqualTo(7)
//             verifyBlocking(repositoryImpl, times(1)) { getSomething() }
//         }
//     }
//
//     @Test
//     fun `function return null in any case`() {
//         assertThat(repositoryImpl.getNothing(any())).isNull()
//         // проверяет факт однократного вызова метода в течении 1000 милисекунд
//         verify(repositoryImpl, timeout(1000).times(1)).getNothing(any())
//
//         // проверяет что ниразу не был вызван этот метод с такими параметрами
//         verify(repositoryImpl, never()).getNothing(7)
//         verifyNoMoreInteractions(repositoryImpl)
//     }
// }

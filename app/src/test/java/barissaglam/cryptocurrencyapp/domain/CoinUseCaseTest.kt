package barissaglam.cryptocurrencyapp.domain

import app.cash.turbine.test
import barissaglam.core.data.ApiResult
import barissaglam.cryptocurrencyapp.ui.utils.MainCoroutineRule
import barissaglam.data.repository.CoinRepositoryImpl
import barissaglam.domain.model.CoinsData
import barissaglam.domain.usecase.CoinUseCase
import com.google.common.truth.Truth.assertThat
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.io.IOException

@ExperimentalCoroutinesApi
class CoinUseCaseTest {

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    private val repository = mockk<CoinRepositoryImpl>()
    private lateinit var useCase: CoinUseCase

    @Before
    fun setUpBefore() {
        useCase = CoinUseCase(repository)
    }

    @Test
    fun `test success case`() = mainCoroutineRule.runBlockingTest {
        //given
        val coinData = mockk<CoinsData>()
        every { repository.getCoins() } returns flow {
            emit(ApiResult.Loading)
            emit(ApiResult.Success(coinData))
        }

        // when
        val actualResult = useCase(Unit)

        //then
        verify(exactly = 1) { repository.getCoins() }

        actualResult.test {
            val loading = awaitItem()
            assertThat(loading).isInstanceOf(ApiResult.Loading::class.java)

            val success = awaitItem()
            assertThat(success).isInstanceOf(ApiResult.Success::class.java)
            assertThat((success as ApiResult.Success).data).isEqualTo(coinData)

            awaitComplete()
        }
    }

    @Test
    fun `test error case`() = mainCoroutineRule.runBlockingTest {
        //given
        val exception = IOException("this is a text exception")
        every { repository.getCoins() } returns flow {
            emit(ApiResult.Loading)
            emit(ApiResult.Error(exception))
        }

        // when
        val action = useCase(Unit)

        //then
        verify(exactly = 1) { repository.getCoins() }

        action.test {
            val loading = awaitItem()
            assertThat(loading).isInstanceOf(ApiResult.Loading::class.java)

            val error = awaitItem() as ApiResult.Error
            assertThat(error).isInstanceOf(ApiResult.Error::class.java)
            assertThat(error.throwable).isEqualTo(exception)
            assertThat(error.throwable.message).isEqualTo("this is a text exception")

            awaitComplete()
        }
    }
}
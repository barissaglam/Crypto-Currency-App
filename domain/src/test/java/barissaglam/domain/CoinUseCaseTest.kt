package barissaglam.domain

import app.cash.turbine.test
import barissaglam.core.data.ApiResult
import barissaglam.domain.model.CoinsData
import barissaglam.domain.repository.CoinRepository
import barissaglam.domain.usecase.CoinUseCase
import barissaglam.domain.utils.MainCoroutineRule
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

    private val repository = mockk<CoinRepository>()
    private lateinit var useCase: CoinUseCase

    @Before
    fun setUpBefore() {
        useCase = CoinUseCase(repository)
    }

    @Test
    fun `test success case`() = mainCoroutineRule.runBlockingTest {
        // given
        val coinData = mockk<CoinsData>()
        every { repository.getCoins() } returns flow {
            emit(ApiResult.Loading)
            emit(ApiResult.Success(coinData))
        }

        // when
        val actualResult = useCase(Unit)

        // then
        verify(exactly = 1) { repository.getCoins() }

        actualResult.test {
            awaitItem().apply {
                assertThat(this).isNotNull()
                assertThat(this).isInstanceOf(ApiResult.Loading::class.java)
            }
            awaitItem().apply {
                assertThat(this).isNotNull()
                assertThat(this).isInstanceOf(ApiResult.Success::class.java)
                this as ApiResult.Success
                assertThat(data).isEqualTo(coinData)
            }
            awaitComplete()
        }
    }

    @Test
    fun `test error case`() = mainCoroutineRule.runBlockingTest {
        // given
        val exception = IOException("this is a text exception")
        every { repository.getCoins() } returns flow {
            emit(ApiResult.Loading)
            emit(ApiResult.Error(exception))
        }

        // when
        val action = useCase(Unit)

        // then
        verify(exactly = 1) { repository.getCoins() }

        action.test {
            awaitItem().apply {
                assertThat(this).isNotNull()
                assertThat(this).isInstanceOf(ApiResult.Loading::class.java)
            }
            awaitItem().apply {
                assertThat(this).isNotNull()
                assertThat(this).isInstanceOf(ApiResult.Error::class.java)
                this as ApiResult.Error
                assertThat(throwable).isEqualTo(exception)
                assertThat(throwable.message).isEqualTo("this is a text exception")
            }
            awaitComplete()
        }
    }
}

package barissaglam.cryptocurrencyapp.domain

import app.cash.turbine.test
import barissaglam.core.data.ApiResult
import barissaglam.cryptocurrencyapp.ui.detail.data.TimePeriod
import barissaglam.cryptocurrencyapp.utils.MainCoroutineRule
import barissaglam.data.repository.CoinDetailRepositoryImpl
import barissaglam.domain.model.Coin
import barissaglam.domain.usecase.CoinDetailUseCase
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
class CoinDetailUseCaseTest {

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    private val repository = mockk<CoinDetailRepositoryImpl>()
    private lateinit var useCase: CoinDetailUseCase

    @Before
    fun setUpBefore() {
        useCase = CoinDetailUseCase(repository)
    }

    @Test
    fun `test success case`() = mainCoroutineRule.runBlockingTest {
        // given
        val coin = mockk<Coin>()
        every { repository.getCoinDetail(uuid = any(), timePeriod = any()) } returns flow {
            emit(ApiResult.Loading)
            emit(ApiResult.Success(coin))
        }

        // when
        val actualResult = useCase(
            CoinDetailUseCase.Params(
                uuid = "test_uuid",
                timePeriod = TimePeriod.DAILY.param
            )
        )

        // then
        verify(exactly = 1) { repository.getCoinDetail(any(), any()) }

        actualResult.test {
            awaitItem().apply {
                assertThat(this).isNotNull()
                assertThat(this).isInstanceOf(ApiResult.Loading::class.java)
            }
            awaitItem().apply {
                assertThat(this).isNotNull()
                assertThat(this).isInstanceOf(ApiResult.Success::class.java)
                this as ApiResult.Success
                assertThat(data).isEqualTo(coin)
            }
            awaitComplete()
        }
    }

    @Test
    fun `test error case`() = mainCoroutineRule.runBlockingTest {
        // given
        val exception = IOException("this is a text exception")
        every { repository.getCoinDetail(uuid = any(), timePeriod = any()) } returns flow {
            emit(ApiResult.Loading)
            emit(ApiResult.Error(exception))
        }

        // when
        val actualResult = useCase(
            CoinDetailUseCase.Params(
                uuid = "test_uuid",
                timePeriod = TimePeriod.DAILY.param
            )
        )

        // then
        verify(exactly = 1) { repository.getCoinDetail(any(), any()) }

        actualResult.test {
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

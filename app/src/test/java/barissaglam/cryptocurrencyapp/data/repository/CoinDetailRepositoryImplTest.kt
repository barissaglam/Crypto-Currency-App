package barissaglam.cryptocurrencyapp.data.repository

import app.cash.turbine.test
import barissaglam.core.data.ApiResult
import barissaglam.cryptocurrencyapp.data.utils.RepositoryFactory
import barissaglam.cryptocurrencyapp.utils.MainCoroutineRule
import barissaglam.data.api.RestApi
import barissaglam.data.mapper.CoinMapper
import barissaglam.data.repository.CoinDetailRepositoryImpl
import barissaglam.domain.model.Coin
import com.google.common.truth.Truth.assertThat
import io.mockk.Called
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.coVerifyOrder
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Rule
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.io.IOException

@ExperimentalCoroutinesApi
class CoinDetailRepositoryImplTest {

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    private val api: RestApi = mockk()
    private val mapper: CoinMapper = mockk()
    private val domainModel: Coin = mockk()

    private lateinit var repository: CoinDetailRepositoryImpl

    @BeforeEach
    fun setUpBefore() {
        every { mapper.toMapUiModel(any()) } returns domainModel
        repository = CoinDetailRepositoryImpl(api, mapper)
    }

    @Test
    fun `when called getCoinDetail(), then result should be success`() = mainCoroutineRule.runBlockingTest {
        // given
        coEvery { api.getCoinDetail(any(), any()) } returns RepositoryFactory.getCoinDetailResponse()

        // when
        val actualResult = repository.getCoinDetail("123", "7d")

        // then
        actualResult.test {
            awaitItem().apply {
                assertThat(this).isNotNull()
                assertThat(this).isInstanceOf(ApiResult.Loading::class.java)
            }
            awaitItem().apply {
                assertThat(this).isNotNull()
                assertThat(this).isInstanceOf(ApiResult.Success::class.java)
                this as ApiResult.Success
                assertThat(data).isInstanceOf(Coin::class.java)
            }
            awaitComplete()
        }

        coVerifyOrder {
            api.getCoinDetail(any(), any())
            mapper.toMapUiModel(any())
        }
    }

    @Test
    fun `when called getCoins(), then result should be error`() = mainCoroutineRule.runBlockingTest {
        // given
        coEvery { api.getCoinDetail(any(), any()) } throws IOException()

        // when
        val actualResult = repository.getCoinDetail("123", "7d")

        // then
        actualResult.test {
            awaitItem().apply {
                assertThat(this).isNotNull()
                assertThat(this).isInstanceOf(ApiResult.Loading::class.java)
            }
            awaitItem().apply {
                assertThat(this).isNotNull()
                assertThat(this).isInstanceOf(ApiResult.Error::class.java)
                this as ApiResult.Error
                assertThat(throwable).isInstanceOf(IOException::class.java)
            }
            awaitComplete()
        }

        coVerify(exactly = 1) { api.getCoinDetail(any(), any()) }
        verify { mapper.toMapUiModel(any()) wasNot Called }
    }
}

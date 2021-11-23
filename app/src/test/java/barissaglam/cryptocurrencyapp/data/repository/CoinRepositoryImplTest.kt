package barissaglam.cryptocurrencyapp.data.repository

import app.cash.turbine.test
import barissaglam.core.data.ApiResult
import barissaglam.cryptocurrencyapp.data.utils.RepositoryFactory
import barissaglam.cryptocurrencyapp.utils.MainCoroutineRule
import barissaglam.data.api.RestApi
import barissaglam.data.mapper.CoinsDataMapper
import barissaglam.data.repository.CoinRepositoryImpl
import barissaglam.domain.model.CoinsData
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
class CoinRepositoryImplTest {

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    private val api: RestApi = mockk()
    private val mapper: CoinsDataMapper = mockk()
    private val domainModel: CoinsData = mockk()

    private lateinit var repository: CoinRepositoryImpl

    @BeforeEach
    fun setUpBefore() {
        every { mapper.toMapUiModel(any()) } returns domainModel
        repository = CoinRepositoryImpl(api, mapper)
    }

    @Test
    fun `when called getCoins(), then result should be success`() = mainCoroutineRule.runBlockingTest {
        // given
        coEvery { api.getCoins() } returns RepositoryFactory.getCoinsResponse()

        // when
        val actualResult = repository.getCoins()

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
                assertThat(data).isInstanceOf(CoinsData::class.java)
            }
            awaitComplete()
        }

        coVerifyOrder {
            api.getCoins()
            mapper.toMapUiModel(any())
        }
    }

    @Test
    fun `when called getCoins(), then result should be error`() = mainCoroutineRule.runBlockingTest {
        // given
        coEvery { api.getCoins() } throws IOException()

        // when
        val actualResult = repository.getCoins()

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

        coVerify(exactly = 1) { api.getCoins() }
        verify { mapper.toMapUiModel(any()) wasNot Called }
    }
}

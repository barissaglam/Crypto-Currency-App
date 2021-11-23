package barissaglam.cryptocurrencyapp.data.api

import barissaglam.core.data.BaseResponse
import barissaglam.cryptocurrencyapp.data.utils.dispatcher.ErrorDispatcher
import barissaglam.cryptocurrencyapp.data.utils.dispatcher.SuccessDispatcher
import barissaglam.cryptocurrencyapp.data.utils.dispatcher.TimeOutDispatcher
import barissaglam.data.api.RestApi
import barissaglam.data.model.CoinDetailDataModel
import barissaglam.data.model.CoinModel
import barissaglam.data.model.CoinsDataModel
import barissaglam.data.model.StatsModel
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.runBlocking
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test
import retrofit2.HttpException
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import java.net.SocketTimeoutException
import java.util.concurrent.TimeUnit

private const val TIMEOUT = 1L
private const val COINS_RESPONSE_FILE_NAME = "coins-response.json"
private const val COIN_DETAIL_RESPONSE_FILE_NAME = "coin-detail-response.json"

class RestApiTest {

    private val mockWebServer = MockWebServer()
    private val okHttpClient = OkHttpClient.Builder()
        .connectTimeout(TIMEOUT, TimeUnit.SECONDS)
        .readTimeout(TIMEOUT, TimeUnit.SECONDS)
        .writeTimeout(TIMEOUT, TimeUnit.SECONDS)
        .build()

    private lateinit var restApi: RestApi

    @Before
    fun setUpBefore() {
        mockWebServer.start(8080)

        restApi = Retrofit.Builder().apply {
            baseUrl(mockWebServer.url("/"))
            client(okHttpClient)
            addConverterFactory(MoshiConverterFactory.create())
        }.build().create()
    }

    /*
        |<<<<<<<<<<<<<<<<<<<<<<|
        |    Coins List Test   |
        |>>>>>>>>>>>>>>>>>>>>>>|
     */

    @Test
    fun `when getCoins(), then result should be success`() = runBlocking {
        // given
        mockWebServer.dispatcher = SuccessDispatcher(COINS_RESPONSE_FILE_NAME)

        // when
        val actualResult = restApi.getCoins()

        // then
        assertThat(actualResult).isNotNull()
        assertThat(actualResult.status).isEqualTo("success")
        assertThat(actualResult.data).isNotNull()
        assertThat(actualResult.data).isInstanceOf(CoinsDataModel::class.java)
        assertThat(actualResult.data.coins).isNotNull()
        assertThat(actualResult.data.coins).hasSize(50)
        assertThat(actualResult.data.stats).isNotNull()
        assertThat(actualResult.data.stats).isInstanceOf(StatsModel::class.java)
    }

    @Test
    fun `when getCoins(), then request should be timeout`() = runBlocking {
        // given
        mockWebServer.dispatcher = TimeOutDispatcher()

        // when
        var actualResult: BaseResponse<CoinsDataModel>? = null

        runCatching {
            actualResult = restApi.getCoins()
        }.exceptionOrNull()?.let {
            assertThat(it).isInstanceOf(SocketTimeoutException::class.java)
            assertThat(it.message).isEqualTo("timeout")
        }

        assertThat(actualResult).isNull()
        assertThat(actualResult?.data).isNull()
        assertThat(actualResult?.status).isNull()
    }

    @Test
    fun `when getCoins(), then request should be error`() = runBlocking {
        // given
        mockWebServer.dispatcher = ErrorDispatcher()

        // when
        var actualResult: BaseResponse<CoinsDataModel>? = null

        runCatching {
            actualResult = restApi.getCoins()
        }.exceptionOrNull()?.let {
            assertThat(it).isInstanceOf(HttpException::class.java)
            assertThat(it.message).isEqualTo("HTTP 500 Server Error")
        }

        assertThat(actualResult).isNull()
        assertThat(actualResult?.data).isNull()
        assertThat(actualResult?.status).isNull()
    }

    /*
        |<<<<<<<<<<<<<<<<<<<<<<|
        |   Coin Detail Test   |
        |>>>>>>>>>>>>>>>>>>>>>>|
     */

    @Test
    fun `when getCoinDetail(), then result should be success`() = runBlocking {
        // given
        mockWebServer.dispatcher = SuccessDispatcher(COIN_DETAIL_RESPONSE_FILE_NAME)

        // when
        val actualResult = restApi.getCoinDetail("Qwsogvtv82FCd", "7d")

        // then
        assertThat(actualResult).isNotNull()
        assertThat(actualResult.status).isEqualTo("success")
        assertThat(actualResult.data).isNotNull()
        assertThat(actualResult.data).isInstanceOf(CoinDetailDataModel::class.java)
        assertThat(actualResult.data.coin).isNotNull()
        assertThat(actualResult.data.coin).isInstanceOf(CoinModel::class.java)
        assertThat(actualResult.data.coin?.uuid).isEqualTo("Qwsogvtv82FCd")
    }

    @Test
    fun `when getCoinDetail(), then request should be timeout`() = runBlocking {
        // given
        mockWebServer.dispatcher = TimeOutDispatcher()

        // when
        var actualResult: BaseResponse<CoinDetailDataModel>? = null

        runCatching {
            actualResult = restApi.getCoinDetail("Qwsogvtv82FCd", "7d")
        }.exceptionOrNull()?.let {
            assertThat(it).isInstanceOf(SocketTimeoutException::class.java)
            assertThat(it.message).isEqualTo("timeout")
        }

        assertThat(actualResult).isNull()
        assertThat(actualResult?.data).isNull()
        assertThat(actualResult?.status).isNull()
    }

    @Test
    fun `when getCoinDetail(), then request should be error`() = runBlocking {
        // given
        mockWebServer.dispatcher = ErrorDispatcher()

        // when
        var actualResult: BaseResponse<CoinDetailDataModel>? = null

        runCatching {
            actualResult = restApi.getCoinDetail("Qwsogvtv82FCd", "7d")
        }.exceptionOrNull()?.let {
            assertThat(it).isInstanceOf(HttpException::class.java)
            assertThat(it.message).isEqualTo("HTTP 500 Server Error")
        }

        assertThat(actualResult).isNull()
        assertThat(actualResult?.data).isNull()
        assertThat(actualResult?.status).isNull()
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }
}

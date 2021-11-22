package barissaglam.cryptocurrencyapp.ui.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import androidx.lifecycle.SavedStateHandle
import barissaglam.core.data.ApiResult
import barissaglam.core.domain.InvalidParamsException
import barissaglam.cryptocurrencyapp.ui.detail.data.DetailLoadingType
import barissaglam.cryptocurrencyapp.ui.detail.data.TimePeriod
import barissaglam.cryptocurrencyapp.ui.utils.MainCoroutineRule
import barissaglam.cryptocurrencyapp.utils.BundleKeys
import barissaglam.cryptocurrencyapp.utils.extensions.getOrAwaitValue
import barissaglam.domain.model.Coin
import barissaglam.domain.usecase.CoinDetailUseCase
import com.google.common.truth.Truth.assertThat
import io.mockk.clearAllMocks
import io.mockk.confirmVerified
import io.mockk.every
import io.mockk.mockk
import io.mockk.spyk
import io.mockk.unmockkAll
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Rule
import org.junit.Test
import org.junit.jupiter.api.assertThrows
import java.io.IOException
import java.util.concurrent.TimeoutException

@ExperimentalCoroutinesApi
class CoinDetailViewModelTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    private val mockUseCase: CoinDetailUseCase = mockk()
    private val mockCoin: Coin = mockk()
    private val mockSavedStateHandle: SavedStateHandle = mockk(relaxed = true)
    private val uiStateObserver: Observer<CoinDetailUiState> = mockk(relaxed = true)

    private val uiStateSlot = mutableListOf<CoinDetailUiState>()
    private val viewModel: CoinDetailViewModel by lazy { CoinDetailViewModel(mockSavedStateHandle, mockUseCase) }

    @Test
    fun `when called getCoins(), then response is success`() = mainCoroutineRule.runBlockingTest {
        // given
        every { mockSavedStateHandle.get<String>(BundleKeys.CoinDetail.KEY_UUID) } returns "123"
        every { mockUseCase(any()) } returns flow {
            emit(ApiResult.Loading)
            emit(ApiResult.Success(mockCoin))
        }
        viewModel.uiStateData.observeForever(uiStateObserver)

        // when
        viewModel.getCoinDetail(TimePeriod.WEEKLY, DetailLoadingType.PROGRESS)

        // then
        verify(exactly = 1) { mockUseCase(any()) }
        verify(exactly = 2) { uiStateObserver.onChanged(capture(uiStateSlot)) }

        // Test UiState LiveData
        uiStateSlot[0].also { coinDetailUiState ->
            assertThat(coinDetailUiState.apiResult).isInstanceOf(ApiResult.Loading::class.java)
            assertThat(coinDetailUiState.loadingType).isEqualTo(DetailLoadingType.PROGRESS)
        }

        uiStateSlot[1].also { coinDetailUiState ->
            assertThat(coinDetailUiState.apiResult).isInstanceOf(ApiResult.Success::class.java)
            assertThat(coinDetailUiState.loadingType).isEqualTo(DetailLoadingType.PROGRESS)
        }

        // Test ViewState LiveData
        assertThat(viewModel.viewStateData.getOrAwaitValue().coin).isEqualTo(mockCoin)
    }

    @Test
    fun `when called getCoins(), then response is error`() = mainCoroutineRule.runBlockingTest {
        // given
        every { mockSavedStateHandle.get<String>(BundleKeys.CoinDetail.KEY_UUID) } returns "123"
        every { mockUseCase(any()) } returns flow {
            emit(ApiResult.Loading)
            emit(ApiResult.Error(IOException("this is a test exception.")))
        }
        viewModel.uiStateData.observeForever(uiStateObserver)

        // when
        viewModel.getCoinDetail(TimePeriod.WEEKLY, DetailLoadingType.SHIMMER)

        // then
        verify(exactly = 1) { mockUseCase(any()) }
        verify(exactly = 2) { uiStateObserver.onChanged(capture(uiStateSlot)) }
        confirmVerified(mockUseCase)

        uiStateSlot[0].also { coinDetailUiState ->
            assertThat(coinDetailUiState.apiResult).isInstanceOf(ApiResult.Loading::class.java)
            assertThat(coinDetailUiState.loadingType).isEqualTo(DetailLoadingType.SHIMMER)
        }

        uiStateSlot[1].also { coinDetailUiState ->
            assertThat(coinDetailUiState.apiResult).isInstanceOf(ApiResult.Error::class.java)
            assertThat(coinDetailUiState.loadingType).isEqualTo(DetailLoadingType.SHIMMER)
        }

        viewModel.errorData.getOrAwaitValue().also { errorViewState ->
            assertThat(errorViewState.throwable).isInstanceOf(IOException::class.java)
            assertThat(errorViewState.throwable.message).isEqualTo("this is a test exception.")
        }

        assertThrows<TimeoutException> { viewModel.viewStateData.getOrAwaitValue() }
    }

    @Test
    fun `test given empty uuid case`() = mainCoroutineRule.runBlockingTest {
        // given
        every { mockSavedStateHandle.get<String>(BundleKeys.CoinDetail.KEY_UUID) } returns null
        val spykUseCase = spyk(CoinDetailUseCase(mockk()))
        val viewModel = CoinDetailViewModel(mockSavedStateHandle, spykUseCase)

        // when
        viewModel.getCoinDetail(TimePeriod.WEEKLY, DetailLoadingType.SHIMMER)

        // then
        verify(exactly = 1) { spykUseCase(any()) }

        viewModel.uiStateData.getOrAwaitValue().also { uiState ->
            assertThat(uiState.apiResult).isInstanceOf(ApiResult.Error::class.java)
            assertThat((uiState.apiResult as ApiResult.Error).throwable).isInstanceOf(InvalidParamsException::class.java)
        }
        viewModel.errorData.getOrAwaitValue().also { errorViewState ->
            assertThat(errorViewState.throwable).isInstanceOf(InvalidParamsException::class.java)
            assertThat(errorViewState.throwable.message).isEqualTo("this is a test exception.")
        }
        assertThrows<TimeoutException> { viewModel.viewStateData.getOrAwaitValue() }
    }

    @After
    fun tearDown() {
        unmockkAll()
        clearAllMocks()
    }
}

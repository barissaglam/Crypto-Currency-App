package barissaglam.cryptocurrencyapp.ui.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import barissaglam.core.data.ApiResult
import barissaglam.cryptocurrencyapp.ui.utils.MainCoroutineRule
import barissaglam.cryptocurrencyapp.utils.extensions.getOrAwaitValue
import barissaglam.domain.model.CoinsData
import barissaglam.domain.usecase.CoinUseCase
import com.google.common.truth.Truth.assertThat
import io.mockk.clearAllMocks
import io.mockk.confirmVerified
import io.mockk.every
import io.mockk.mockk
import io.mockk.unmockkAll
import io.mockk.verify
import io.mockk.verifySequence
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class HomeViewModelTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    private val mockUseCase = mockk<CoinUseCase>()
    private val mockCoinsData = mockk<CoinsData>()
    private val homeUiStateSlot = mutableListOf<HomeUiState>()
    private val homeUiStateObserver = mockk<Observer<HomeUiState>>(relaxed = true)
    private lateinit var viewModel: HomeViewModel

    @Before
    fun setUpBefore() {
        viewModel = HomeViewModel(mockUseCase)
    }

    @Test
    fun `test when called getCoins() and response is success`() = runBlockingTest {
        // given
        viewModel.uiStateData.observeForever(homeUiStateObserver)
        val successResult = ApiResult.Success(mockCoinsData)

        every { mockUseCase(Unit) } returns flow {
            emit(ApiResult.Loading)
            emit(successResult)
        }

        // when
        viewModel.getCoins()

        // then
        verify(exactly = 1) { mockUseCase(Unit) }
        verify(exactly = 2) { homeUiStateObserver.onChanged(capture(homeUiStateSlot)) }
        confirmVerified(mockUseCase)

        // Test UiState LiveData
        verifySequence {
            homeUiStateObserver.onChanged(HomeUiState(ApiResult.Loading))
            homeUiStateObserver.onChanged(HomeUiState(successResult))
        }

        homeUiStateSlot[0].also { loadingStateLiveData ->
            assertThat(loadingStateLiveData.isShowShimmer()).isTrue()
            assertThat(loadingStateLiveData.isShowContent()).isFalse()
        }

        homeUiStateSlot[1].also { successStateLiveData ->
            assertThat(successStateLiveData.isShowShimmer()).isFalse()
            assertThat(successStateLiveData.isShowContent()).isTrue()
        }

        // Test ViewState LiveData
        assertThat(viewModel.viewStateData.getOrAwaitValue().coinData).isEqualTo(mockCoinsData)
    }

    @Test
    fun `test when called getCoins() and response is error`() = runBlockingTest {
        // given
        viewModel.uiStateData.observeForever(homeUiStateObserver)
        val errorResult = ApiResult.Error(Throwable("this is a test exception."))

        every { mockUseCase(Unit) } returns flow {
            emit(ApiResult.Loading)
            emit(errorResult)
        }

        // when
        viewModel.getCoins()

        // then
        verify(exactly = 1) { mockUseCase(Unit) }
        verify(exactly = 2) { homeUiStateObserver.onChanged(capture(homeUiStateSlot)) }
        confirmVerified(mockUseCase)

        // Test UiState LiveData
        verifySequence {
            homeUiStateObserver.onChanged(HomeUiState(ApiResult.Loading))
            homeUiStateObserver.onChanged(HomeUiState(errorResult))
        }

        homeUiStateSlot[0].also { loadingStateLiveData ->
            assertThat(loadingStateLiveData.isShowShimmer()).isTrue()
            assertThat(loadingStateLiveData.isShowContent()).isFalse()
        }

        homeUiStateSlot[1].also { errorStateLiveData ->
            assertThat(errorStateLiveData.isShowShimmer()).isFalse()
            assertThat(errorStateLiveData.isShowContent()).isFalse()
        }

        // Test Error LiveData
        assertThat(viewModel.errorData.getOrAwaitValue().throwable.message)
            .isEqualTo("this is a test exception.")
    }

    @After
    fun tearDown() {
        unmockkAll()
        clearAllMocks()
    }
}

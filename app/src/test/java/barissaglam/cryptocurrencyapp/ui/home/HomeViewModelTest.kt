package barissaglam.cryptocurrencyapp.ui.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import barissaglam.core.data.ApiResult
import barissaglam.cryptocurrencyapp.utils.MainCoroutineRule
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
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.jupiter.api.assertThrows
import java.io.IOException
import java.util.concurrent.TimeoutException

@ExperimentalCoroutinesApi
class HomeViewModelTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    private val mockUseCase: CoinUseCase = mockk()
    private val mockCoinsData: CoinsData = mockk()
    private val uiStatObserver: Observer<HomeUiState> = mockk(relaxed = true)

    private val uiStateSlot = mutableListOf<HomeUiState>()

    private lateinit var viewModel: HomeViewModel

    @Before
    fun setUpBefore() {
        viewModel = HomeViewModel(mockUseCase)
        viewModel.uiStateData.observeForever(uiStatObserver)
    }

    @Test
    fun `when called getCoins(), then response is success`() = runBlockingTest {
        // given
        every { mockUseCase(Unit) } returns flow {
            emit(ApiResult.Loading)
            emit(ApiResult.Success(mockCoinsData))
        }

        // when
        viewModel.getCoins()

        // then
        verify(exactly = 1) { mockUseCase(Unit) }
        verify(exactly = 2) { uiStatObserver.onChanged(capture(uiStateSlot)) }

        // Test UiState LiveData
        assertThat(uiStateSlot[0].apiResult).isInstanceOf(ApiResult.Loading::class.java)
        assertThat(uiStateSlot[1].apiResult).isInstanceOf(ApiResult.Success::class.java)

        // Test ViewState LiveData
        assertThat(viewModel.viewStateData.getOrAwaitValue().coinData).isEqualTo(mockCoinsData)
    }

    @Test
    fun `when called getCoins(), then response is error`() = runBlockingTest {
        // given
        every { mockUseCase(Unit) } returns flow {
            emit(ApiResult.Loading)
            emit(ApiResult.Error(IOException("this is a test exception.")))
        }

        // when
        viewModel.getCoins()

        // then
        verify(exactly = 1) { mockUseCase(Unit) }
        verify(exactly = 2) { uiStatObserver.onChanged(capture(uiStateSlot)) }
        confirmVerified(mockUseCase)

        assertThat(uiStateSlot[0].apiResult).isInstanceOf(ApiResult.Loading::class.java)
        assertThat(uiStateSlot[1].apiResult).isInstanceOf(ApiResult.Error::class.java)

        viewModel.errorData.getOrAwaitValue().also { errorViewState ->
            assertThat(errorViewState.throwable).isInstanceOf(IOException::class.java)
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

package barissaglam.cryptocurrencyapp.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import barissaglam.core.extension.onResultChanged
import barissaglam.core.extension.onSuccess
import barissaglam.core.view.BaseViewModel
import barissaglam.domain.usecase.CoinUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val coinUseCase: CoinUseCase,
) : BaseViewModel() {

    private val uiState = MutableLiveData<HomeUiState>()
    val uiStateData: LiveData<HomeUiState> by ::uiState

    private val viewState = MutableLiveData<HomeViewState>()
    val viewStateData: LiveData<HomeViewState> by ::viewState

    internal fun getCoins() {
        sendRequest(
            callFunc = { coinUseCase(Unit) },
            retryFunc = { getCoins() }
        ).onResultChanged { apiResult ->
            uiState.value = HomeUiState(apiResult)
        }.onSuccess { coinsData ->
            viewState.value = HomeViewState(coinsData)
        }.launch()
    }
}

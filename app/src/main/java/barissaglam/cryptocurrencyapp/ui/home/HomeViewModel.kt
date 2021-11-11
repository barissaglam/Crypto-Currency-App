package barissaglam.cryptocurrencyapp.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import barissaglam.core.extension.onSuccess
import barissaglam.core.view.BaseViewModel
import barissaglam.domain.model.CoinsData
import barissaglam.domain.usecase.CoinUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val coinUseCase: CoinUseCase,
) : BaseViewModel() {

    private val uiViewState = MutableLiveData<HomeUiViewState>()
    val uiViewStateData: LiveData<HomeUiViewState> by ::uiViewState

    private val viewState = MutableLiveData<HomeViewState>()
    val viewStateData: LiveData<HomeViewState> by ::viewState

    init {
        getCoins()
    }

    private fun getCoins() {
        sendRequest(
            callFunc = { coinUseCase(Unit) },
            retryFunc = ::getCoins
        ).onSuccess { coinsData ->
            publishCoinsData(coinsData)
        }.onEach { resource ->
            uiViewState.value = HomeUiViewState(resource)
        }.launch()
    }

    fun publishCoinsData(coinsData: CoinsData) {
        viewState.value?.let {
            viewState.value = it.copy(coinData = coinsData)
        } ?: run {
            viewState.value = HomeViewState(coinData = coinsData)
        }
    }
}

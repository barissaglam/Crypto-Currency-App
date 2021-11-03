package barissaglam.cryptocurrencyapp.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import barissaglam.core.view.BaseViewModel
import barissaglam.data.model.uimodel.CoinsData
import barissaglam.domain.usecase.CoinUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val coinUseCase: CoinUseCase,
) : BaseViewModel() {

    private val _viewState = MutableLiveData<HomeViewState>()
    val viewState: LiveData<HomeViewState> by ::_viewState

    init {
        getCoins()
    }

    private fun getCoins() {
        sendRequest(
            callFunc = { coinUseCase(Unit) },
            retryFunc = { getCoins() }
        ) { coinsData ->
            updateCoinsData(coinsData)
        }
    }

    fun updateCoinsData(coinsData: CoinsData) {
        _viewState.value?.let {
            _viewState.value = it.copy(coinData = coinsData)
        } ?: run {
            _viewState.value = HomeViewState(coinData = coinsData)
        }
    }
}

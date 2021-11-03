package barissaglam.cryptocurrencyapp.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import barissaglam.core.view.BaseViewModel
import barissaglam.cryptocurrencyapp.utils.BundleKeys.CoinDetail
import barissaglam.domain.usecase.CoinDetailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CoinDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val coinDetailUseCase: CoinDetailUseCase
) : BaseViewModel() {

    private val _viewState = MutableLiveData<CoinDetailViewState>()
    val viewState: LiveData<CoinDetailViewState> by ::_viewState

    init {
        savedStateHandle.get<String>(CoinDetail.KEY_UUID)?.let { uuid ->
            getCoinDetail(uuid)
        }
    }

    private fun getCoinDetail(uuid: String) {
        sendRequest(
            callFunc = { coinDetailUseCase(CoinDetailUseCase.Params(uuid)) },
            retryFunc = { getCoinDetail(uuid) }
        ) { coin ->
            _viewState.value = CoinDetailViewState(coin)
        }
    }
}

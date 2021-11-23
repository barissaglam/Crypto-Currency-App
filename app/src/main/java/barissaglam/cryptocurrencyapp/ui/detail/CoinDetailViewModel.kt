package barissaglam.cryptocurrencyapp.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import barissaglam.core.extension.onResultChanged
import barissaglam.core.extension.onSuccess
import barissaglam.core.view.BaseViewModel
import barissaglam.cryptocurrencyapp.ui.detail.data.DetailLoadingType
import barissaglam.cryptocurrencyapp.ui.detail.data.TimePeriod
import barissaglam.cryptocurrencyapp.utils.BundleKeys.CoinDetail
import barissaglam.domain.usecase.CoinDetailUseCase
import barissaglam.domain.usecase.CoinDetailUseCase.Params
import barissaglam.extensions.EMPTY_STRING
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CoinDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val coinDetailUseCase: CoinDetailUseCase
) : BaseViewModel() {

    private var uuid: String = EMPTY_STRING

    private val viewState = MutableLiveData<CoinDetailViewState>()
    val viewStateData: LiveData<CoinDetailViewState> by ::viewState

    private val uiState = MutableLiveData<CoinDetailUiState>()
    val uiStateData: LiveData<CoinDetailUiState> by ::uiState

    init {
        savedStateHandle.get<String>(CoinDetail.KEY_UUID)?.let { uuid ->
            this.uuid = uuid
        }
    }

    fun getCoinDetail(timePeriod: TimePeriod, loadingType: DetailLoadingType) {
        sendRequest(
            callFunc = { coinDetailUseCase(Params(uuid, timePeriod.param)) },
            retryFunc = { getCoinDetail(timePeriod, loadingType) }
        ).onResultChanged { apiResult ->
            uiState.value = CoinDetailUiState(apiResult, loadingType)
        }.onSuccess { coin ->
            viewState.value = CoinDetailViewState(coin, timePeriod)
        }.launch()
    }
}

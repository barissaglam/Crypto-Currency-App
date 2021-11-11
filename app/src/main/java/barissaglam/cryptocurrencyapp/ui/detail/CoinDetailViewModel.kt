package barissaglam.cryptocurrencyapp.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import barissaglam.core.extension.onSuccess
import barissaglam.core.view.BaseViewModel
import barissaglam.cryptocurrencyapp.utils.BundleKeys.CoinDetail
import barissaglam.domain.usecase.CoinDetailUseCase
import barissaglam.domain.usecase.CoinDetailUseCase.Params
import barissaglam.extensions.EMPTY_STRING
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class CoinDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val coinDetailUseCase: CoinDetailUseCase
) : BaseViewModel() {

    private var uuid: String = EMPTY_STRING

    private val viewState = MutableLiveData<CoinDetailViewState>()
    val viewStateData: LiveData<CoinDetailViewState> by ::viewState

    private val uiViewState = MutableLiveData<CoinDetailUiViewState>()
    val uiViewStateData: LiveData<CoinDetailUiViewState> by ::uiViewState

    init {
        savedStateHandle.get<String>(CoinDetail.KEY_UUID)?.let { uuid ->
            this.uuid = uuid
            getCoinDetail(TimePeriod.DAILY, DetailLoadingType.Shimmer)
        }
    }

    fun getCoinDetail(timePeriod: TimePeriod, loadingType: DetailLoadingType) {
        sendRequest(
            callFunc = { coinDetailUseCase(Params(uuid, timePeriod.param)) },
            retryFunc = { getCoinDetail(timePeriod, loadingType) }
        ).onSuccess { coin ->
            viewState.value = CoinDetailViewState(coin, timePeriod)
        }.onEach { resource ->
            uiViewState.value = CoinDetailUiViewState(resource, loadingType)
        }.launch()
    }
}

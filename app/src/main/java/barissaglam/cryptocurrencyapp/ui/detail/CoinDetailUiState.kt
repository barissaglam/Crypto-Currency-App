package barissaglam.cryptocurrencyapp.ui.detail

import barissaglam.core.data.ApiResult
import barissaglam.cryptocurrencyapp.ui.detail.data.DetailLoadingType
import barissaglam.cryptocurrencyapp.ui.detail.data.DetailLoadingType.PROGRESS
import barissaglam.cryptocurrencyapp.ui.detail.data.DetailLoadingType.SHIMMER

data class CoinDetailUiState(
    val apiResult: ApiResult<*>,
    val loadingType: DetailLoadingType
) {

    fun isShowShimmer(): Boolean {
        return apiResult is ApiResult.Loading && loadingType == SHIMMER
    }

    fun isShowProgress(): Boolean {
        return apiResult is ApiResult.Loading && loadingType == PROGRESS
    }

    fun isShowContent(): Boolean {
        return apiResult is ApiResult.Success || isShowProgress()
    }
}

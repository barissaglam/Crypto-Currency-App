package barissaglam.cryptocurrencyapp.ui.home

import barissaglam.core.data.ApiResult

data class HomeUiState(val apiResult: ApiResult<*>) {

    fun isShowShimmer(): Boolean {
        return apiResult is ApiResult.Loading
    }

    fun isShowContent(): Boolean {
        return apiResult is ApiResult.Success
    }
}

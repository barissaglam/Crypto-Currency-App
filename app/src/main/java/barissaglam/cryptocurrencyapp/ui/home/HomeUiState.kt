package barissaglam.cryptocurrencyapp.ui.home

import barissaglam.core.data.ApiResult

data class HomeUiState(private val apiResult: ApiResult<*>) {

    fun isShowShimmer(): Boolean {
        return apiResult is ApiResult.Loading
    }

    fun isShowContent(): Boolean {
        return apiResult is ApiResult.Success
    }

    fun qweqweqweqwewq(): String? {
        if (apiResult is ApiResult.Error) {
            return apiResult.throwable.message
        }

        return null
    }
}

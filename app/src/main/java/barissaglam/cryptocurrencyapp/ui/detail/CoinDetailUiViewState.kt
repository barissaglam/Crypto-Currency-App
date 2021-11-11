package barissaglam.cryptocurrencyapp.ui.detail

import barissaglam.core.data.Resource

data class CoinDetailUiViewState(
    val resource: Resource<Any>,
    val loadingType: DetailLoadingType
) {

    fun isShowShimmer(): Boolean {
        return resource is Resource.Loading && loadingType == DetailLoadingType.Shimmer
    }

    fun isShowProgress(): Boolean {
        return resource is Resource.Loading && loadingType == DetailLoadingType.Progress
    }

    fun isShowContent(): Boolean {
        return resource is Resource.Success || isShowProgress()
    }
}
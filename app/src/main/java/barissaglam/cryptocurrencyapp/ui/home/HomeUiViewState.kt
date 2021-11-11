package barissaglam.cryptocurrencyapp.ui.home

import barissaglam.core.data.Resource

data class HomeUiViewState(
    private val resource: Resource<Any>
) {

    fun isShowShimmer(): Boolean {
        return resource is Resource.Loading
    }

    fun isShowContent(): Boolean {
        return resource is Resource.Success
    }
}

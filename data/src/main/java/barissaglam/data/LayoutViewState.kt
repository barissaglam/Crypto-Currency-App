package barissaglam.data

import barissaglam.core.data.Resource

class LayoutViewState(
    private val resource: Resource<Any>
) {

    fun isLoading() = resource is Resource.Loading

    fun isError() = resource is Resource.Error

    fun isSuccess() = resource is Resource.Success
}

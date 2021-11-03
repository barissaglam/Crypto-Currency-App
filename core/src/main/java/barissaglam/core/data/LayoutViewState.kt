package barissaglam.core.data

class LayoutViewState(
    private val resource: Resource<Any>
) {

    fun isLoading(): Boolean {
        return resource is Resource.Loading
    }

    fun isError(): Boolean {
        return resource is Resource.Error
    }

    fun isSuccess(): Boolean {
        return resource is Resource.Success
    }
}

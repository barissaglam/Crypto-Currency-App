package barissaglam.core.data

sealed class ApiResult<out T> {

    class Success<T>(val data: T) : ApiResult<T>()
    class Error(val throwable: Throwable) : ApiResult<Nothing>()
    object Loading : ApiResult<Nothing>()
}

package barissaglam.core.extension

import barissaglam.core.data.ApiResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.transform

fun <T, R> ApiResult<T>.map(transform: (T) -> R): ApiResult<R> {
    return when (this) {
        is ApiResult.Loading -> ApiResult.Loading
        is ApiResult.Success -> ApiResult.Success(transform(data))
        is ApiResult.Error -> ApiResult.Error(throwable)
    }
}

fun <T> Flow<ApiResult<T>>.onResultChanged(action: (ApiResult<T>) -> Unit): Flow<ApiResult<T>> {
    return transform { apiResult ->
        action.invoke(apiResult)
        emit(apiResult)
    }
}

fun <T> Flow<ApiResult<T>>.onSuccess(action: (T) -> Unit): Flow<ApiResult<T>> {
    return transform { apiResult ->
        if (apiResult is ApiResult.Success) {
            action.invoke(apiResult.data)
        }
        emit(apiResult)
    }
}

fun <T> Flow<ApiResult<T>>.onError(action: (Throwable) -> Unit): Flow<ApiResult<T>> {
    return transform { apiResult ->
        if (apiResult is ApiResult.Error) {
            action.invoke(apiResult.throwable)
        }
        emit(apiResult)
    }
}

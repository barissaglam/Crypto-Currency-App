package barissaglam.core.extension

import barissaglam.core.data.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.transform

fun <T, R> Resource<T>.map(transform: (T) -> R): Resource<R> {
    return when (this) {
        is Resource.Loading -> Resource.Loading
        is Resource.Success -> Resource.Success(transform(data))
        is Resource.Error -> Resource.Error(throwable)
    }
}

fun <T> Flow<Resource<T>>.onLoading(action: () -> Unit): Flow<Resource<T>> {
    return transform { resource ->
        if (resource is Resource.Loading) {
            action.invoke()
        }
        emit(resource)
    }
}

fun <T> Flow<Resource<T>>.onSuccess(action: (T) -> Unit): Flow<Resource<T>> {
    return transform { resource ->
        if (resource is Resource.Success) {
            action.invoke(resource.data)
        }
        emit(resource)
    }
}

fun <T> Flow<Resource<T>>.onError(action: (Throwable) -> Unit): Flow<Resource<T>> {
    return transform { resource ->
        if (resource is Resource.Error) {
            action.invoke(resource.throwable)
        }
        emit(resource)
    }
}

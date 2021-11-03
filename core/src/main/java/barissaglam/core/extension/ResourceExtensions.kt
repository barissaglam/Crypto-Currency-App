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

fun <T> Flow<Resource<T>>.onSuccess(action: (T) -> Unit): Flow<Resource<T>> {
    return transform { value ->
        if (value is Resource.Success) {
            action.invoke(value.data)
        }
        emit(value)
    }
}

fun <T> Flow<Resource<T>>.onError(action: (Throwable) -> Unit): Flow<Resource<T>> {
    return transform { value ->
        if (value is Resource.Error) {
            action.invoke(value.throwable)
        }
        emit(value)
    }
}

package barissaglam.core.view

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import barissaglam.core.data.Resource
import barissaglam.core.extension.onError
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.launchIn

abstract class BaseViewModel : ViewModel() {

    private val _error = MutableSharedFlow<BaseError>(0, 1, BufferOverflow.DROP_OLDEST)
    val error: SharedFlow<BaseError> by ::_error

    fun <T : Any> sendRequest(
        callFunc: () -> Flow<Resource<T>>,
        retryFunc: () -> Unit
    ): Flow<Resource<T>> {
        return callFunc.invoke().apply {
            onError { throwable ->
                publishError(throwable, retryFunc)
            }
        }
    }

    private fun publishError(throwable: Throwable, retryFunc: () -> Unit) {
        _error.tryEmit(BaseError(throwable = throwable, retryFunc = retryFunc))
    }


    data class BaseError(
        val throwable: Throwable,
        val retryFunc: () -> Unit
    )

    fun <T> Flow<Resource<T>>.launch() {
        launchIn(viewModelScope)
    }

}

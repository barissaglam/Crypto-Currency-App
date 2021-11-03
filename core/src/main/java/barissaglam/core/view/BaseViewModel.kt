package barissaglam.core.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import barissaglam.core.data.LayoutViewState
import barissaglam.core.data.Resource
import barissaglam.core.extension.onError
import barissaglam.core.extension.onSuccess
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

abstract class BaseViewModel : ViewModel() {

    private val _error = MutableSharedFlow<BaseError>(
        extraBufferCapacity = 1,
        onBufferOverflow = BufferOverflow.DROP_OLDEST
    )
    val error: SharedFlow<BaseError> by ::_error

    private val _layoutViewState = MutableLiveData<LayoutViewState>()
    val layoutViewState: LiveData<LayoutViewState> by ::_layoutViewState

    fun <T : Any> sendRequest(
        callFunc: () -> Flow<Resource<T>>,
        retryFunc: (() -> Unit)? = null,
        successFunc: (T) -> Unit
    ) {
        callFunc.invoke().apply {
            onEach { resource ->
                _layoutViewState.value = LayoutViewState(resource)
            }.onSuccess { data ->
                successFunc(data)
            }.onError { throwable ->
                _error.tryEmit(BaseError(throwable = throwable, retryFunc = retryFunc))
            }.launchIn(viewModelScope)
        }
    }

    data class BaseError(
        val throwable: Throwable,
        val retryFunc: (() -> Unit)? = null
    )
}

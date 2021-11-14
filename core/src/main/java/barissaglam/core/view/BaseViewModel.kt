package barissaglam.core.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import barissaglam.core.data.ApiResult
import barissaglam.core.extension.onError
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.launchIn

abstract class BaseViewModel : ViewModel() {

    private val error = MutableLiveData<BaseError>()
    val errorData: LiveData<BaseError> by ::error

    fun <T : Any> sendRequest(
        callFunc: () -> Flow<ApiResult<T>>,
        retryFunc: () -> Unit
    ): Flow<ApiResult<T>> {
        return callFunc.invoke().apply {
            onError { throwable ->
                publishError(throwable, retryFunc)
            }.launch()
        }
    }

    private fun publishError(throwable: Throwable, retryFunc: () -> Unit) {
        error.postValue(BaseError(throwable = throwable, retryFunc = retryFunc))
    }


    data class BaseError(
        val throwable: Throwable,
        val retryFunc: () -> Unit
    )

    fun <T> Flow<ApiResult<T>>.launch() {
        launchIn(viewModelScope)
    }

}

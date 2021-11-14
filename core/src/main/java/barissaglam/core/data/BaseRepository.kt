package barissaglam.core.data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onStart

abstract class BaseRepository {

    fun <T> callApi(block: suspend () -> T): Flow<ApiResult<T>> {
        return flow {
            emit(ApiResult.Success(block.invoke()) as ApiResult<T>)
        }
            .onStart { emit(ApiResult.Loading) }
            .catch { throwable -> emit(ApiResult.Error(throwable)) }
    }
}

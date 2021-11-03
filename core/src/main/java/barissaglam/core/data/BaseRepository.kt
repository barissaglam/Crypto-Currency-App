package barissaglam.core.data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onStart

abstract class BaseRepository {

    fun <T> callApi(block: suspend () -> T): Flow<Resource<T>> {
        return flow {
            emit(Resource.Success(block.invoke()) as Resource<T>)
        }
            .onStart { emit(Resource.Loading) }
            .catch { throwable -> emit(Resource.Error(throwable)) }
    }
}

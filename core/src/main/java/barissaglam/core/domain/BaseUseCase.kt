package barissaglam.core.domain

import barissaglam.core.data.ApiResult
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn

abstract class BaseUseCase<Params, Response>(
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) {

    operator fun invoke(parameters: Params): Flow<ApiResult<Response>> {
        return execute(parameters)
            .flowOn(dispatcher)
    }

    protected abstract fun execute(parameters: Params): Flow<ApiResult<Response>>
}

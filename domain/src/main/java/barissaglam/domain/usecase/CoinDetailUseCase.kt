package barissaglam.domain.usecase

import barissaglam.core.data.ApiResult
import barissaglam.core.domain.BaseUseCase
import barissaglam.core.domain.InvalidParamsException
import barissaglam.domain.model.Coin
import barissaglam.domain.repository.CoinDetailRepository
import barissaglam.extensions.EMPTY_STRING
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CoinDetailUseCase @Inject constructor(
    private val repository: CoinDetailRepository
) : BaseUseCase<CoinDetailUseCase.Params, Coin>() {

    override fun execute(parameters: Params): Flow<ApiResult<Coin>> {
        return when (parameters.uuid) {
            EMPTY_STRING -> {
                flow { emit(ApiResult.Error(InvalidParamsException())) }
            }
            else -> {
                repository.getCoinDetail(uuid = parameters.uuid, timePeriod = parameters.timePeriod)
            }
        }
    }

    data class Params(
        val uuid: String,
        val timePeriod: String
    )
}

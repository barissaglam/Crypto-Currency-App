package barissaglam.domain.usecase

import barissaglam.core.data.ApiResult
import barissaglam.core.domain.BaseUseCase
import barissaglam.domain.model.Coin
import barissaglam.domain.repository.CoinDetailRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CoinDetailUseCase @Inject constructor(
    private val repository: CoinDetailRepository
) : BaseUseCase<CoinDetailUseCase.Params, Coin>() {

    override fun execute(parameters: Params): Flow<ApiResult<Coin>> {
        return with(parameters) {
            repository.getCoinDetail(uuid = uuid, timePeriod = timePeriod)
        }
    }

    data class Params(
        val uuid: String,
        val timePeriod: String
    )
}

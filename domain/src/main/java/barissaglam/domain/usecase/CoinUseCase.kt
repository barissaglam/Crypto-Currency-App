package barissaglam.domain.usecase

import barissaglam.core.data.ApiResult
import barissaglam.core.domain.BaseUseCase
import barissaglam.domain.model.CoinsData
import barissaglam.domain.repository.CoinRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CoinUseCase @Inject constructor(
    private val repository: CoinRepository
) : BaseUseCase<Unit, CoinsData>() {

    override fun execute(parameters: Unit): Flow<ApiResult<CoinsData>> {
        return repository.getCoins()
    }
}

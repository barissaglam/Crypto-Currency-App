package barissaglam.domain.usecase

import barissaglam.core.data.Resource
import barissaglam.core.domain.BaseUseCase
import barissaglam.core.extension.map
import barissaglam.data.model.uimodel.CoinsData
import barissaglam.data.repository.CoinRepository
import barissaglam.domain.mapper.CoinsDataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CoinUseCase @Inject constructor(
    private val repository: CoinRepository,
    private val mapper: CoinsDataMapper
) : BaseUseCase<Unit, CoinsData>() {

    override fun execute(parameters: Unit): Flow<Resource<CoinsData>> {
        return repository.getCoins().map {
            it.map { coinData ->
                mapper.toMapUiModel(coinData.data)
            }
        }
    }
}

package barissaglam.domain.usecase

import barissaglam.core.data.Resource
import barissaglam.core.domain.BaseUseCase
import barissaglam.core.extension.map
import barissaglam.data.model.uimodel.Coin
import barissaglam.data.repository.CoinRepository
import barissaglam.domain.mapper.CoinMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CoinDetailUseCase @Inject constructor(
    private val repository: CoinRepository,
    private val mapper: CoinMapper
) : BaseUseCase<CoinDetailUseCase.Params, Coin>() {

    override fun execute(parameters: Params): Flow<Resource<Coin>> {
        return repository.getCoinDetail(parameters.uuid).map {
            it.map { coinData ->
                mapper.toMapUiModel(coinData.data.coin)
            }
        }
    }

    data class Params(
        val uuid: String
    )
}

package barissaglam.domain.usecase

import barissaglam.core.data.Resource
import barissaglam.core.domain.BaseUseCase
import barissaglam.core.extension.map
import barissaglam.data.model.uimodel.ExchangesData
import barissaglam.data.repository.ExchangeRepository
import barissaglam.domain.mapper.ExchangesDataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ExchangeUseCase @Inject constructor(
    private val repository: ExchangeRepository,
    private val mapper: ExchangesDataMapper
) : BaseUseCase<Unit, ExchangesData>() {

    override fun execute(parameters: Unit): Flow<Resource<ExchangesData>> {
        return repository.getExchanges().map {
            it.map { exchangesData ->
                mapper.toMapUiModel(exchangesData.data)
            }
        }
    }
}

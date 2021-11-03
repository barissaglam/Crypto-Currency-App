package barissaglam.domain.mapper

import barissaglam.core.data.Mapper
import barissaglam.data.model.ExchangesDataModel
import barissaglam.data.model.uimodel.ExchangesData
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ExchangesDataMapper @Inject constructor(
    private val statsMapper: StatsMapper,
    private val exchangeMapper: ExchangeMapper
) : Mapper<ExchangesDataModel, ExchangesData> {

    override fun toMapUiModel(model: ExchangesDataModel): ExchangesData = with(model) {
        ExchangesData(
            stats = statsMapper.toMapUiModel(model.stats),
            exchanges = exchanges?.map { exchangeModel ->
                exchangeMapper.toMapUiModel(exchangeModel)
            }.orEmpty()
        )
    }
}

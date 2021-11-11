package barissaglam.data.mapper

import barissaglam.core.data.Mapper
import barissaglam.data.model.CoinsDataModel
import barissaglam.domain.model.CoinsData
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CoinsDataMapper @Inject constructor(
    private val statsMapper: StatsMapper,
    private val coinMapper: CoinMapper
) : Mapper<CoinsDataModel, CoinsData> {

    override fun toMapUiModel(model: CoinsDataModel): CoinsData = with(model) {
        CoinsData(
            stats = statsMapper.toMapUiModel(model.stats),
            coins = coins?.map { coinModel ->
                coinMapper.toMapUiModel(coinModel)
            }.orEmpty()
        )
    }
}

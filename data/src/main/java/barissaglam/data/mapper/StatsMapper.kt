package barissaglam.data.mapper

import barissaglam.core.data.Mapper
import barissaglam.data.model.StatsModel
import barissaglam.domain.model.Stats
import barissaglam.extensions.orZero
import barissaglam.extensions.toBigDecimalOrZero
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class StatsMapper @Inject constructor() : Mapper<StatsModel?, Stats> {

    override fun toMapUiModel(model: StatsModel?): Stats {
        return Stats(
            total = model?.total.orZero(),
            totalCoins = model?.totalCoins.orZero(),
            totalMarkets = model?.totalMarkets.orZero(),
            totalExchanges = model?.totalExchanges.orZero(),
            totalMarketCap = model?.totalMarketCap.toBigDecimalOrZero(),
            total24hVolume = model?.total24hVolume.toBigDecimalOrZero()
        )
    }
}

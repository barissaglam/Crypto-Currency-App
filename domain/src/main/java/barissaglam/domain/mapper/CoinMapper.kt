package barissaglam.domain.mapper

import barissaglam.core.data.Mapper
import barissaglam.data.model.CoinModel
import barissaglam.data.model.uimodel.AllTimeHigh
import barissaglam.data.model.uimodel.Coin
import barissaglam.data.model.uimodel.Supply
import barissaglam.extensions.orFalse
import barissaglam.extensions.orZero
import barissaglam.extensions.toBigDecimalOrZero
import barissaglam.extensions.toDoubleOrZero
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CoinMapper @Inject constructor() : Mapper<CoinModel?, Coin> {

    override fun toMapUiModel(model: CoinModel?): Coin {
        return Coin(
            uuid = model?.uuid.orEmpty(),
            symbol = model?.symbol.orEmpty(),
            name = model?.name.orEmpty(),
            description = model?.description.orEmpty(),
            color = model?.color.orEmpty(),
            iconUrl = model?.iconUrl.orEmpty(),
            supply = Supply(
                confirmed = model?.supply?.confirmed.orFalse(),
                total = model?.supply?.total.toBigDecimalOrZero(),
                circulating = model?.supply?.circulating.toBigDecimalOrZero()
            ),
            marketCap = model?.marketCap.toBigDecimalOrZero(),
            price = model?.price.toBigDecimalOrZero(),
            btcPrice = model?.btcPrice.toBigDecimalOrZero(),
            listedAt = model?.listedAt.orZero(),
            change = model?.change.toDoubleOrZero(),
            rank = model?.rank.orZero(),
            sparkline = model?.sparkline?.map { sparkLine ->
                sparkLine.toBigDecimalOrZero()
            }.orEmpty(),
            coinRankingUrl = model?.coinRankingUrl.orEmpty(),
            volume24h = model?.volume24h.toBigDecimalOrZero(),
            allTimeHigh = AllTimeHigh(
                price = model?.allTimeHigh?.price.toBigDecimalOrZero(),
                timestamp = model?.allTimeHigh?.timestamp.orZero()
            )
        )
    }
}

package barissaglam.domain.mapper

import barissaglam.core.data.Mapper
import barissaglam.data.model.ExchangeModel
import barissaglam.data.model.uimodel.Exchange
import barissaglam.extensions.orFalse
import barissaglam.extensions.orZero
import barissaglam.extensions.toBigDecimalOrZero
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ExchangeMapper @Inject constructor() : Mapper<ExchangeModel?, Exchange> {

    override fun toMapUiModel(model: ExchangeModel?): Exchange {
        return Exchange(
            uuid = model?.uuid.orEmpty(),
            name = model?.name.orEmpty(),
            iconUrl = model?.iconUrl.orEmpty(),
            rank = model?.rank.orZero(),
            coinRankingUrl = model?.coinRankingUrl.orEmpty(),
            volume24h = model?.volume24h.toBigDecimalOrZero(),
            verified = model?.verified.orFalse(),
            recommended = model?.recommended.orFalse(),
            numberOfMarkets = model?.numberOfMarkets.orZero(),
            numberOfCoins = model?.numberOfCoins.orZero(),
            marketShare = model?.marketShare.orZero()
        )
    }
}

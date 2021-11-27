package barissaglam.data.utils

import barissaglam.core.data.BaseResponse
import barissaglam.data.model.AllTimeHighModel
import barissaglam.data.model.CoinDetailDataModel
import barissaglam.data.model.CoinModel
import barissaglam.data.model.CoinsDataModel
import barissaglam.data.model.StatsModel
import barissaglam.data.model.SupplyModel

object RepositoryNullFactory {

    fun getCoinsResponse(): BaseResponse<CoinsDataModel> = BaseResponse(
        status = "test_status",
        data = getCoinsDataModel()
    )

    fun getCoinDetailResponse(): BaseResponse<CoinDetailDataModel> = BaseResponse(
        status = "test_status",
        data = getCoinDetailModel()
    )

    fun getCoinsDataModel() = CoinsDataModel(
        stats = null,
        coins = null
    )

    fun getStatsModel() = StatsModel(
        total = null,
        totalCoins = null,
        totalMarkets = null,
        totalExchanges = null,
        totalMarketCap = null,
        total24hVolume = null
    )

    fun getCoinModel() = CoinModel(
        uuid = null,
        symbol = null,
        name = null,
        description = null,
        color = null,
        iconUrl = null,
        websiteUrl = null,
        supply = null,
        marketCap = null,
        price = null,
        btcPrice = null,
        listedAt = null,
        change = null,
        rank = null,
        sparkline = null,
        allTimeHigh = null,
        coinRankingUrl = null,
        volume24h = null
    )

    fun getSupplyModel() = SupplyModel(
        confirmed = null,
        total = null,
        circulating = null
    )

    fun getAllTimeHigh() = AllTimeHighModel(
        price = null,
        timestamp = null
    )

    fun getCoinDetailModel() = CoinDetailDataModel(
        coin = null
    )
}

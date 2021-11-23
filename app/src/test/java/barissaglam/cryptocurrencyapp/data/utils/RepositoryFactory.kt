package barissaglam.cryptocurrencyapp.data.utils

import barissaglam.core.data.BaseResponse
import barissaglam.data.model.AllTimeHighModel
import barissaglam.data.model.CoinDetailDataModel
import barissaglam.data.model.CoinModel
import barissaglam.data.model.CoinsDataModel
import barissaglam.data.model.StatsModel
import barissaglam.data.model.SupplyModel

object RepositoryFactory {

    fun getCoinsResponse(): BaseResponse<CoinsDataModel> = BaseResponse(
        status = "test_status",
        data = getCoinsDataModel()
    )

    fun getCoinDetailResponse(): BaseResponse<CoinDetailDataModel> = BaseResponse(
        status = "test_status",
        data = getCoinDetailModel()
    )

    fun getCoinsDataModel() = CoinsDataModel(
        stats = getStatsModel(),
        coins = listOf(
            getCoinModel(),
            getCoinModel(),
            getCoinModel(),
            getCoinModel(),
            getCoinModel(),
            getCoinModel()
        )
    )

    fun getStatsModel() = StatsModel(
        total = 10,
        totalCoins = 50,
        totalMarkets = 500,
        totalExchanges = 250,
        totalMarketCap = "758",
        total24hVolume = "1312"
    )

    fun getCoinModel() = CoinModel(
        uuid = "123",
        symbol = "BTC",
        name = "Bitcoin",
        description = "Desc Bitcoin",
        color = "#DFDFDF",
        iconUrl = "http://test.icon.url.png",
        websiteUrl = "http://test.icon.url.png",
        supply = getSupplyModel(),
        marketCap = "31243",
        price = "54654654",
        btcPrice = "345435",
        listedAt = 1232432,
        change = "234",
        rank = 345345,
        sparkline = listOf(
            "213",
            "32423",
            "56756"
        ),
        allTimeHigh = getAllTimeHigh(),
        coinRankingUrl = "http://coin.ranking.url",
        volume24h = "234324324"
    )

    fun getSupplyModel() = SupplyModel(
        confirmed = true,
        "132132",
        "2321312"
    )

    fun getAllTimeHigh() = AllTimeHighModel(
        price = "32324",
        timestamp = 213213
    )

    fun getCoinDetailModel() = CoinDetailDataModel(
        coin = getCoinModel()
    )
}

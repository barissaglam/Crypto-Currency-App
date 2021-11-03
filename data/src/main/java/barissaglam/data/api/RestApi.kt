package barissaglam.data.api

import barissaglam.data.model.BaseResponse
import barissaglam.data.model.CoinDetailDataModel
import barissaglam.data.model.CoinsDataModel
import barissaglam.data.model.ExchangesDataModel
import retrofit2.http.GET
import retrofit2.http.Path

interface RestApi {

    @GET(COINS)
    suspend fun getCoins(): BaseResponse<CoinsDataModel>

    @GET(COIN_DETAIL)
    suspend fun getCoinDetail(
        @Path("uuid") uuid: String
    ): BaseResponse<CoinDetailDataModel>

    @GET(EXCHANGES)
    suspend fun getExchanges(): BaseResponse<ExchangesDataModel>

    private companion object EndPoints {
        const val COINS = "coins"
        const val COIN_DETAIL = "coin/{uuid}"
        const val EXCHANGES = "exchanges"
    }
}

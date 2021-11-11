package barissaglam.data.api

import barissaglam.core.data.BaseResponse
import barissaglam.data.model.CoinDetailDataModel
import barissaglam.data.model.CoinsDataModel
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RestApi {

    @GET(COINS)
    suspend fun getCoins(): BaseResponse<CoinsDataModel>

    @GET(COIN_DETAIL)
    suspend fun getCoinDetail(
        @Path("uuid") uuid: String,
        @Query("timePeriod") timePeriod: String
    ): BaseResponse<CoinDetailDataModel>

    private companion object EndPoints {
        const val COINS = "coins"
        const val COIN_DETAIL = "coin/{uuid}"
    }
}

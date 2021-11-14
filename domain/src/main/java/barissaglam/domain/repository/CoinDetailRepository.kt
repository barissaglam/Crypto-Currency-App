package barissaglam.domain.repository

import barissaglam.core.data.ApiResult
import barissaglam.domain.model.Coin
import kotlinx.coroutines.flow.Flow

interface CoinDetailRepository {

    fun getCoinDetail(uuid: String, timePeriod: String): Flow<ApiResult<Coin>>
}

package barissaglam.domain.repository

import barissaglam.core.data.Resource
import barissaglam.domain.model.Coin
import kotlinx.coroutines.flow.Flow

interface CoinDetailRepository {

    fun getCoinDetail(uuid: String, timePeriod: String): Flow<Resource<Coin>>
}

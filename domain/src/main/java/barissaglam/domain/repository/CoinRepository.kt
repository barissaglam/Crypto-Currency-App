package barissaglam.domain.repository

import barissaglam.core.data.Resource
import barissaglam.domain.model.CoinsData
import kotlinx.coroutines.flow.Flow

interface CoinRepository {

    fun getCoins(): Flow<Resource<CoinsData>>
}

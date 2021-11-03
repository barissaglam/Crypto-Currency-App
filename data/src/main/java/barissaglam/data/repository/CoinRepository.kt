package barissaglam.data.repository

import barissaglam.core.data.BaseRepository
import barissaglam.data.api.RestApi
import javax.inject.Inject

class CoinRepository @Inject constructor(
    private val api: RestApi
) : BaseRepository() {

    fun getCoins() = callApi {
        api.getCoins()
    }

    fun getCoinDetail(uuid: String) = callApi {
        api.getCoinDetail(uuid)
    }
}

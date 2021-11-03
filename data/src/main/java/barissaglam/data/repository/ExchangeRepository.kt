package barissaglam.data.repository

import barissaglam.core.data.BaseRepository
import barissaglam.data.api.RestApi
import javax.inject.Inject

class ExchangeRepository @Inject constructor(
    private val api: RestApi
) : BaseRepository() {

    fun getExchanges() = callApi {
        api.getExchanges()
    }
}

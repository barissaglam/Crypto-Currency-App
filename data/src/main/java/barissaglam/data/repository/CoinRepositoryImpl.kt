package barissaglam.data.repository

import barissaglam.core.data.BaseRepository
import barissaglam.core.extension.map
import barissaglam.data.api.RestApi
import barissaglam.data.mapper.CoinsDataMapper
import barissaglam.domain.repository.CoinRepository
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CoinRepositoryImpl @Inject constructor(
    private val api: RestApi,
    private val coinsDataMapper: CoinsDataMapper
) : CoinRepository, BaseRepository() {

    override fun getCoins() = callApi { api.getCoins() }
        .map { apiResult ->
            apiResult.map { response ->
                coinsDataMapper.toMapUiModel(response.data)
            }
        }
}

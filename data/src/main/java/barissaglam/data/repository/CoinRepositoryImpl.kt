package barissaglam.data.repository

import barissaglam.core.data.BaseRepository
import barissaglam.core.extension.map
import barissaglam.data.api.RestApi
import barissaglam.data.mapper.CoinMapper
import barissaglam.data.mapper.CoinsDataMapper
import barissaglam.domain.repository.CoinRepository
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CoinRepositoryImpl @Inject constructor(
    private val api: RestApi,
    private val coinMapper: CoinMapper,
    private val coinsDataMapper: CoinsDataMapper
) : CoinRepository, BaseRepository() {

    override fun getCoins() = callApi { api.getCoins() }
        .map { resource ->
            resource.map { response ->
                coinsDataMapper.toMapUiModel(response.data)
            }
        }
}

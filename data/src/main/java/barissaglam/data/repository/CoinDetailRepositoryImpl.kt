package barissaglam.data.repository

import barissaglam.core.data.BaseRepository
import barissaglam.core.extension.map
import barissaglam.data.api.RestApi
import barissaglam.data.mapper.CoinMapper
import barissaglam.domain.repository.CoinDetailRepository
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CoinDetailRepositoryImpl @Inject constructor(
    private val api: RestApi,
    private val coinMapper: CoinMapper
) : CoinDetailRepository, BaseRepository() {

    override fun getCoinDetail(uuid: String, timePeriod: String) = callApi { api.getCoinDetail(uuid, timePeriod) }
        .map { resource ->
            resource.map { response ->
                coinMapper.toMapUiModel(response.data.coin)
            }
        }
}

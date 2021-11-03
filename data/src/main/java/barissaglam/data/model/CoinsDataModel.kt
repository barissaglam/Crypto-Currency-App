package barissaglam.data.model

import com.squareup.moshi.Json

class CoinsDataModel(

    @field:Json(name = "stats")
    val stats: StatsModel? = null,

    @field:Json(name = "coins")
    val coins: List<CoinModel>? = null,
)

package barissaglam.data.model

import com.squareup.moshi.Json

class ExchangesDataModel(

    @field:Json(name = "stats")
    val stats: StatsModel? = null,

    @field:Json(name = "exchanges")
    val exchanges: List<ExchangeModel>? = null,
)

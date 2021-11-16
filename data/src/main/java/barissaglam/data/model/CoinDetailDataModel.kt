package barissaglam.data.model

import com.squareup.moshi.Json

class CoinDetailDataModel(

    @field:Json(name = "coin")
    val coin: CoinModel? = null
)

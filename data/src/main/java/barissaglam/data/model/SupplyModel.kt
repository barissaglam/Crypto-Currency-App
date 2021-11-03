package barissaglam.data.model

import com.squareup.moshi.Json

class SupplyModel(

    @field:Json(name = "confirmed")
    val confirmed: Boolean? = null,

    @field:Json(name = "total")
    val total: String? = null,

    @field:Json(name = "circulating")
    val circulating: String? = null,
)

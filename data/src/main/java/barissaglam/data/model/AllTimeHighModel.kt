package barissaglam.data.model

import com.squareup.moshi.Json

class AllTimeHighModel(

    @field:Json(name = "price")
    val price: String? = null,

    @field:Json(name = "timestamp")
    val timestamp: Long? = null
)

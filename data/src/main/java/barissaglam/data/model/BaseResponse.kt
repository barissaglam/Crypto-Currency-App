package barissaglam.data.model

import com.squareup.moshi.Json

class BaseResponse<T>(

    @field:Json(name = "status")
    val status: String,

    @field:Json(name = "data")
    val data: T,
)

package barissaglam.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.math.BigDecimal

@Parcelize
class Supply(
    val confirmed: Boolean,
    val total: BigDecimal,
    val circulating: BigDecimal
) : Parcelable

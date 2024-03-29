package barissaglam.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.math.BigDecimal

@Parcelize
class AllTimeHigh(
    val price: BigDecimal,
    val timestamp: Long
) : Parcelable

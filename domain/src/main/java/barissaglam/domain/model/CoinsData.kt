package barissaglam.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class CoinsData(
    val stats: Stats,
    val coins: List<Coin>
) : Parcelable

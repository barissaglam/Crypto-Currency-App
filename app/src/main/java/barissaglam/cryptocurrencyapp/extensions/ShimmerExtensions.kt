package barissaglam.cryptocurrencyapp.extensions

import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter
import com.facebook.shimmer.ShimmerFrameLayout

@BindingAdapter("isShimmerShow")
fun ShimmerFrameLayout.isShimmerShow(show: Boolean) {
    isVisible = if (show) {
        startShimmer()
        true
    } else {
        stopShimmer()
        false
    }
}

package barissaglam.cryptocurrencyapp.extensions

import android.view.View
import androidx.databinding.BindingAdapter
import barissaglam.extensions.gone
import barissaglam.extensions.show

@BindingAdapter("isVisible")
fun View.isVisible(isVisible: Boolean) {
    if (isVisible) {
        show()
    } else {
        gone()
    }
}

package barissaglam.cryptocurrencyapp.extensions

import android.widget.TextView
import androidx.databinding.BindingAdapter

@BindingAdapter("selected")
fun TextView.selected(selected: Boolean) {
    isSelected = selected
}

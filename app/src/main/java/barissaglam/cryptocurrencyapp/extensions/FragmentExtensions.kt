package barissaglam.cryptocurrencyapp.extensions

import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData

fun <T> Fragment.observe(liveData: LiveData<T>, observeFun: (T) -> Unit) {
    liveData.observe(viewLifecycleOwner, observeFun)
}

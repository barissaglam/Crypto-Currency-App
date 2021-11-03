package barissaglam.core.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class BaseViewHolder<M : AdapterItem>(
    private val view: View,
) : RecyclerView.ViewHolder(view) {

    abstract fun bind(data: M)
}

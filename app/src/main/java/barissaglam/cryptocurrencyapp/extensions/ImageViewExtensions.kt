package barissaglam.cryptocurrencyapp.extensions

import android.content.res.ColorStateList
import android.net.Uri
import android.widget.ImageView
import androidx.core.widget.ImageViewCompat
import androidx.databinding.BindingAdapter
import barissaglam.extensions.asColor
import com.bumptech.glide.Glide

@BindingAdapter("imageUrl")
fun ImageView.loadImage(imageUrl: String?) {
    if (imageUrl.isNullOrEmpty()) return

    val uri = Uri.parse(imageUrl)

    Glide.with(context)
        .load(uri)
        .into(this)
}

@BindingAdapter("tintFromStr")
fun ImageView.tintFromString(color: String?) {
    val colorInt = color.asColor()
    ImageViewCompat.setImageTintList(this, ColorStateList.valueOf(colorInt))
}

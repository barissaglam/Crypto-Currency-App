package barissaglam.cryptocurrencyapp.utils

import android.content.Context
import android.graphics.drawable.PictureDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.Registry
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.load.Options
import com.bumptech.glide.load.ResourceDecoder
import com.bumptech.glide.load.engine.Resource
import com.bumptech.glide.load.resource.SimpleResource
import com.bumptech.glide.load.resource.transcode.ResourceTranscoder
import com.bumptech.glide.module.AppGlideModule
import com.bumptech.glide.request.target.Target.SIZE_ORIGINAL
import com.caverock.androidsvg.SVG
import java.io.IOException
import java.io.InputStream

/**
 * Reference from https://github.com/bumptech/glide/tree/master/samples/svg/src/main/java/com/bumptech/glide/samples/svg
 */

@GlideModule
class GlideModule : AppGlideModule() {
    override fun registerComponents(context: Context, glide: Glide, registry: Registry) {
        with(registry) {
            append(InputStream::class.java, SVG::class.java, SvgDecoder())
            register(SVG::class.java, PictureDrawable::class.java, SvgDrawableTranscoder())
        }
    }
}

class SvgDecoder : ResourceDecoder<InputStream, SVG> {
    override fun handles(source: InputStream, options: Options): Boolean {
        return true
    }

    @Throws(IOException::class)
    override fun decode(source: InputStream, width: Int, height: Int, options: Options): Resource<SVG> {
        try {
            return SVG.getFromInputStream(source).let { svg ->
                if (width != SIZE_ORIGINAL) {
                    svg.documentWidth = width.toFloat()
                }
                if (height != SIZE_ORIGINAL) {
                    svg.documentHeight = height.toFloat()
                }
                SimpleResource(svg)
            }
        } catch (exception: Exception) {
            throw exception
        }
    }
}

class SvgDrawableTranscoder : ResourceTranscoder<SVG, PictureDrawable> {
    override fun transcode(toTranscode: Resource<SVG>, options: Options): Resource<PictureDrawable> {
        return toTranscode.get().renderToPicture().let { picture ->
            SimpleResource(
                PictureDrawable(picture)
            )
        }
    }
}

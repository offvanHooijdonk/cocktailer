package by.off.cocktailer.service

import android.widget.ImageView
import androidx.annotation.DrawableRes
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.module.AppGlideModule
import com.bumptech.glide.request.transition.DrawableCrossFadeFactory

@GlideModule
class GlideAppModule : AppGlideModule()

fun ImageView.loadLargeImage(url: String?, @DrawableRes placeHolderId: Int) {
    GlideApp.with(this.context)
        .load(url)
        .transition(DrawableTransitionOptions.withCrossFade(crossFadeFactory))
        //.fallback(R.drawable.ic_museum_24)
        .placeholder(placeHolderId)
        .into(this)
}

private val crossFadeFactory = DrawableCrossFadeFactory.Builder().setCrossFadeEnabled(true).build()
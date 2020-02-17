package by.off.cocktailer.ui

import android.os.Build
import android.view.View
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import by.off.cocktailer.R

@ColorInt
fun View.getColor(@ColorRes colorRes: Int) =
    if (Build.VERSION.SDK_INT > Build.VERSION_CODES.M) {
        context.getColor(colorRes)
    } else {
        context.resources.getColor(colorRes)
    }

fun SwipeRefreshLayout.setupTheme() {
    setColorSchemeColors(
        getColor(R.color.swipe_refresh_1),
        getColor(R.color.swipe_refresh_2),
        getColor(R.color.swipe_refresh_3)
    )
}
package by.off.cocktailer.ui

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import by.off.cocktailer.R
import by.off.cocktailer.model.CocktailModel
import by.off.cocktailer.service.loadLargeImage
import by.off.cocktailer.ui.cocktail.list.CocktailAdapter

@BindingAdapter("cocktailsList")
fun setCocktailsList(rv: RecyclerView, list: List<CocktailModel>) {
    (rv.adapter as? CocktailAdapter)?.update(list)
}

@BindingAdapter("isRefreshing")
fun setRefreshing(refreshLayout: SwipeRefreshLayout, isRefreshing: Boolean) {
    refreshLayout.isRefreshing = isRefreshing
}

@BindingAdapter("cocktailLargeImage")
fun setLargeCocktailImage(img: ImageView, url: String?) {
    img.loadLargeImage(url, R.drawable.ic_cocktail_placeholder) // todo add better placeholder/fallback
}
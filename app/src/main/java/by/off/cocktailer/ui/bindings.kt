package by.off.cocktailer.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import by.off.cocktailer.R
import by.off.cocktailer.model.CocktailComponentIngredients
import by.off.cocktailer.model.CocktailWithComponents
import by.off.cocktailer.service.loadLargeImage
import by.off.cocktailer.ui.cocktail.list.CocktailAdapter
import com.google.android.material.chip.Chip

@BindingAdapter("cocktailsList")
fun setCocktailsList(rv: RecyclerView, list: List<CocktailWithComponents>) {
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

@BindingAdapter("ingredients")
fun setIngredientsToItem(container: ViewGroup, list: List<CocktailComponentIngredients>) {
    val inflater = LayoutInflater.from(container.context)
    //3val colors = container.context.resources.getIntArray(R.array.chip_colors)
    list.forEach {
        val view = inflater.inflate(R.layout.item_ingridient_chip, container, false)
        val chip = view.findViewById<Chip>(R.id.chip_ingredient)
        chip.text = it.ingredient?.name ?: it.drink?.name
        /*val index = (it.ingredient?.id?.hashCode() ?: it.drink?.id?.hashCode() ?: 0) % colors.size
        val color = colors[index]
        chip.chipBackgroundColor = ColorStateList.valueOf(color)*/
        container.addView(view)
    }
}
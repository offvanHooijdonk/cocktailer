package by.off.cocktailer.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import by.off.cocktailer.R
import by.off.cocktailer.model.CocktailComponentIngredients
import by.off.cocktailer.model.CocktailWithComponents
import by.off.cocktailer.model.TagModel
import by.off.cocktailer.service.loadLargeImage
import by.off.cocktailer.ui.cocktail.list.CocktailAdapter
import com.google.android.material.chip.Chip
import java.text.DecimalFormat

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

@BindingAdapter("components")
fun setIngredientsToItem(container: ViewGroup, list: List<CocktailComponentIngredients>) {
    val inflater = LayoutInflater.from(container.context)
    container.removeAllViews()
    //val colors = container.context.resources.getIntArray(R.array.chip_colors)
    list.filter { it.drink != null }.forEach {
        val view = inflater.inflate(R.layout.item_ingridient_chip, container, false)
        /*val txtIngredient = view.findViewById<TextView>(R.id.txt_ingredient)
        txtIngredient.text = it.ingredient?.name ?: it.drink?.name*/
        val chip = view.findViewById<Chip>(R.id.chip_ingredient)
        chip.text = it.ingredient?.name ?: it.drink?.name

        container.addView(view)
    }
}

@BindingAdapter("tags")
fun setTagsToItem(container: ViewGroup, list: List<TagModel>) {
    val inflater = LayoutInflater.from(container.context)
    container.removeAllViews()
    //val colors = container.context.resources.getIntArray(R.array.chip_colors)
    list.sortedBy { it.title }.forEach {
        val view = inflater.inflate(R.layout.item_tag_chip, container, false)
        /*val chip = view.findViewById<Chip>(R.id.chip_tag)
        chip.text = it.title*/
        val txtIngredient = view.findViewById<TextView>(R.id.txt_tag)
        txtIngredient.text = it.title
        container.addView(view)
    }
}

@BindingAdapter("volume")
fun setCocktailVolume(textView: TextView, volume: Float) {
    textView.text = textView.context.getString(R.string.unit_gram_short, DecimalFormat("0.#").format(volume))
}

@BindingAdapter("volume")
fun setCocktailVolumeChip(chip: Chip, volume: Float) {
    chip.text = chip.context.getString(R.string.unit_gram_short, DecimalFormat("0.#").format(volume))
}
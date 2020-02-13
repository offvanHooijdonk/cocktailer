package by.off.cocktailer.ui.cocktail.list

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import by.off.cocktailer.model.CocktailModel

class ItemCocktailViewModel : ViewModel() {
    val cocktail = ObservableField<CocktailModel>()
}
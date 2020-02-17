package by.off.cocktailer.ui.cocktail.list

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import by.off.cocktailer.model.CocktailWithComponents

class ItemCocktailViewModel : ViewModel() {
    val entity = ObservableField<CocktailWithComponents>()
}
package by.off.cocktailer.ui.cocktail.list

import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableBoolean
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import by.off.cocktailer.model.CocktailModel
import by.off.cocktailer.repo.CocktailRepo
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class CocktailListViewModel(private val cocktailRepo: CocktailRepo) : ViewModel() {
    val list = ObservableArrayList<CocktailModel>()
    val progressLoading = ObservableBoolean(false)

    init {
        loadData()
    }

    fun reloadData() {
        progressLoading.set(true)
        loadData()
    }

    private fun loadData() {
        cocktailRepo.listAll()
            .onEach {
                list.apply { clear(); addAll(it) }
                progressLoading.set(false)
            }
            .launchIn(viewModelScope)
    }
}
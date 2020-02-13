package by.off.cocktailer.ui.main

import androidx.lifecycle.ViewModel
import by.off.cocktailer.repo.CocktailRepo

class MainViewModel(private val cocktailRepo: CocktailRepo) : ViewModel() {

    fun initData() {
        cocktailRepo.initData()
    }
}
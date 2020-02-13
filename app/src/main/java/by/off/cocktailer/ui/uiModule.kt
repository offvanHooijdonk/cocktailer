package by.off.cocktailer.ui

import by.off.cocktailer.ui.cocktail.list.CocktailListViewModel
import by.off.cocktailer.ui.dashboard.DashboardViewModel
import by.off.cocktailer.ui.home.HomeViewModel
import by.off.cocktailer.ui.main.MainViewModel
import by.off.cocktailer.ui.notifications.NotificationsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val uiModule = module {
    // FIXME remove those
    viewModel { DashboardViewModel() }
    viewModel { HomeViewModel() }
    viewModel { NotificationsViewModel() }

    viewModel { MainViewModel(get()) }
    viewModel { CocktailListViewModel(get()) }
}
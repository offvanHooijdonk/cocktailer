package by.off.cocktailer.repo

import org.koin.dsl.module

val repoModule = module {
    single { CocktailRepo(get()) }
}
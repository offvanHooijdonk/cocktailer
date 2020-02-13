package by.off.cocktailer.dao

import org.koin.dsl.module

val daoModule = module {
    single { RoomDB.create(get()) }
    single { get<RoomDB>().cocktailDao() }
    single { get<RoomDB>().cocktailComponentDao() }
    single { get<RoomDB>().drinkDao() }
    single { get<RoomDB>().ingridientDao() }
}
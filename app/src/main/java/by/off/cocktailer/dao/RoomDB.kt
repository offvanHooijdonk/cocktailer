package by.off.cocktailer.dao

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import by.off.cocktailer.model.*

@Database(
    version = 1,
    entities = [CocktailModel::class, CocktailComponentModel::class, IngredientModel::class, DrinkModel::class, TagModel::class, CocktailsTagModel::class]
)
@TypeConverters(by.off.cocktailer.dao.TypeConverters::class)
abstract class RoomDB : RoomDatabase() {
    companion object {
        private const val DB_NAME = "cocktails-db-0.04"

        fun create(context: Context) = Room.databaseBuilder(context, RoomDB::class.java, DB_NAME).build()
    }

    abstract fun cocktailDao(): CocktailDao
    abstract fun cocktailComponentDao(): CocktailComponentDao
    abstract fun drinkDao(): DrinkDao
    abstract fun ingredientDao(): IngredientDao
    abstract fun tagDao(): TagDao
}
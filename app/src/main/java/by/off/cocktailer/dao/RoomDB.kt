package by.off.cocktailer.dao

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import by.off.cocktailer.model.CocktailComponentModel
import by.off.cocktailer.model.CocktailModel
import by.off.cocktailer.model.DrinkModel
import by.off.cocktailer.model.IngredientModel

@Database(
    version = 1,
    entities = [CocktailModel::class, CocktailComponentModel::class, IngredientModel::class, DrinkModel::class]
)
@TypeConverters(by.off.cocktailer.dao.TypeConverters::class)
abstract class RoomDB : RoomDatabase() {
    companion object {
        private const val DB_NAME = "cocktails-db-0.03"

        fun create(context: Context) = Room.databaseBuilder(context, RoomDB::class.java, DB_NAME).build()
    }

    abstract fun cocktailDao(): CocktailDao
    abstract fun cocktailComponentDao(): CocktailComponentDao
    abstract fun drinkDao(): DrinkDao
    abstract fun ingredientDao(): IngredientDao
}
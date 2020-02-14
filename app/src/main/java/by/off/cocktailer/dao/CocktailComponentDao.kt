package by.off.cocktailer.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import by.off.cocktailer.model.CocktailComponentModel

@Dao
interface CocktailComponentDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(model: CocktailComponentModel)

    @Query("select * from Components where cocktailId = :cocktailId")
    fun getComponentsForCocktail(cocktailId: Long): List<CocktailComponentModel>

    @Query("delete from Components")
    fun clearAll()
}
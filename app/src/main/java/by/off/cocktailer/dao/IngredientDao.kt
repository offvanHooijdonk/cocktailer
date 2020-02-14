package by.off.cocktailer.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import by.off.cocktailer.model.IngredientModel

@Dao
interface IngredientDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(model: IngredientModel)

    @Query("select * from Ingredients where id = :id")
    fun getById(id: Long): IngredientModel

    @Query("delete from Ingredients")
    fun clearAll()
}
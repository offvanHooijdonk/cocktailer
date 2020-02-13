package by.off.cocktailer.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import by.off.cocktailer.model.CocktailModel
import kotlinx.coroutines.flow.Flow

@Dao
interface CocktailDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE) // todo alter strategy on for implementation?
    fun insert(model: CocktailModel): Long

    @Query("select * from Cocktails order by name")
    fun listAll(): Flow<List<CocktailModel>>

    @Query("delete from Cocktails")
    fun clearAll()
}
package by.off.cocktailer.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import by.off.cocktailer.model.DrinkModel

@Dao
interface DrinkDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(model: DrinkModel)

    @Query("select * from Drinks where id = :id")
    fun getById(id: Long): DrinkModel

    @Query("delete from Drinks")
    fun clearAll()
}
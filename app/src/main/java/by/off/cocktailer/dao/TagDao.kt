package by.off.cocktailer.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import by.off.cocktailer.model.CocktailsTagModel
import by.off.cocktailer.model.TagModel

@Dao
interface TagDao {
    @Insert
    fun insertTag(model: TagModel)

    @Insert
    fun insertCocktailTag(model: CocktailsTagModel)

    @Query("delete from CocktailTags")
    fun clearAllTags()

    @Query("delete from TagsForCocktails")
    fun clearAllCocktailTags()
}
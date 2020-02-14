package by.off.cocktailer.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

interface Ingredient {
    val id: Long
    val name: String
}

@Entity(tableName = "Ingredients")
data class IngredientModel(
    @PrimaryKey/*(autoGenerate = true)*/
    @ColumnInfo(index = true)
    override val id: Long,
    override val name: String
): Ingredient

@Entity(tableName = "Drinks")
data class DrinkModel(
    @PrimaryKey/*(autoGenerate = true)*/
    @ColumnInfo(index = true)
    override val id: Long,
    override val name: String,
    val alcoholVolume: Float,
    val imageUrl: String?
): Ingredient
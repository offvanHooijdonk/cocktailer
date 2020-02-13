package by.off.cocktailer.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Cocktails")
data class CocktailModel(
    @PrimaryKey/*(autoGenerate = true)*/
    @ColumnInfo(index = true)
    val id: Long,
    val name: String,
    val instruction: String,
    val imageUrl: String? = null
)

@Entity(tableName = "Components")
data class CocktailComponentModel(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(index = true)
    val id: Long,
    val ingredientId: Long,
    val ingredientType: ComponentType,
    val cocktailId: Long,
    val amount: Float,
    val unit: ComponentUnit
)

enum class ComponentType {
    INGREDIENT, DRINK;

    companion object {
        fun default() = INGREDIENT

        fun fromCode(code: Int) = values().getOrNull(code) ?: default()
    }

    val code: Int
        get() = this.ordinal
}

enum class ComponentUnit {
    UNIT, GRAM, PORTION;

    companion object {
        fun default() = UNIT

        fun fromCode(code: Int) = values().getOrNull(code) ?: default()
    }

    val code: Int
        get() = this.ordinal
}
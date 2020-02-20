package by.off.cocktailer.model

import androidx.room.*

@Entity(tableName = "Cocktails")
data class CocktailModel(
    @PrimaryKey/*(autoGenerate = true)*/
    @ColumnInfo(index = true, name = "cocktailId")
    val id: Long,
    val name: String,
    val instruction: String,
    val volume: Float,
    val imageUrl: String? = null
)

data class CocktailWithComponents(
    @Embedded
    val cocktail: CocktailModel,
    @Relation(entity = CocktailComponentModel::class, parentColumn = "cocktailId", entityColumn = "cocktailId")
    val components: List<CocktailComponentIngredients>,
    @Relation(parentColumn = "cocktailId", entityColumn = "tagId", associateBy = Junction(CocktailsTagModel::class))
    val tags: List<TagModel>
)

@Entity(tableName = "Components")
data class CocktailComponentModel(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(index = true)
    val id: Long,
    val cocktailId: Long,
    val ingredientId: Long?,
    val drinkId: Long?,
    val amount: Float,
    val unit: ComponentUnit
) {
    class Builder(private val cocktailId: Long) {
        private var ingredientId: Long? = null
        private var drinkId: Long? = null
        private var amount: Float = 0.0f
        private var unit = ComponentUnit.default()

        fun ingredient(id: Long, amount: Float, unit: ComponentUnit = ComponentUnit.default()) =
            this.apply {
                ingredientId = id
                drinkId = null
                this.amount = amount
                this.unit = unit
            }

        fun drink(id: Long, gram: Float) =
            this.apply {
                drinkId = id
                ingredientId = null
                this.amount = gram
                this.unit = ComponentUnit.GRAM
            }

        fun build() = CocktailComponentModel(0, cocktailId, ingredientId, drinkId, amount, unit)
    }
}

data class CocktailComponentIngredients(
    @Embedded
    val component: CocktailComponentModel, // todo try order by here
    @Relation(entity = IngredientModel::class, parentColumn = "ingredientId", entityColumn = "id")
    val ingredient: IngredientModel? = null,
    @Relation(entity = DrinkModel::class, parentColumn = "drinkId", entityColumn = "id")
    val drink: DrinkModel? = null
)

@Entity(tableName = "CocktailTags")
data class TagModel(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(index = true, name = "tagId")
    val id: Long,
    val title: String
)

@Entity(tableName = "TagsForCocktails", primaryKeys = ["cocktailId", "tagId"])
data class CocktailsTagModel(
    @ColumnInfo(index = true)
    val cocktailId: Long,
    @ColumnInfo(index = true)
    val tagId: Long
)

/*enum class ComponentType {
    INGREDIENT, DRINK;

    companion object {
        fun default() = INGREDIENT

        fun fromCode(code: Int) = values().getOrNull(code) ?: default()
    }

    val code: Int
        get() = this.ordinal
}*/

enum class ComponentUnit {
    UNIT, GRAM, PORTION;

    companion object {
        fun default() = GRAM

        fun fromCode(code: Int) = values().getOrNull(code) ?: default()
    }

    val code: Int
        get() = this.ordinal
}
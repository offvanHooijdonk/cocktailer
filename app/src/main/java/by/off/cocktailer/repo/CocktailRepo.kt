package by.off.cocktailer.repo

import by.off.cocktailer.dao.*
import by.off.cocktailer.model.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch

class CocktailRepo(
    private val cocktailDao: CocktailDao,
    private val cocktailComponentDao: CocktailComponentDao,
    private val drinkDao: DrinkDao,
    private val ingredientDao: IngredientDao,
    private val tagDao: TagDao
) {

    fun initData() {
        CoroutineScope(Dispatchers.IO).launch {
            initCocktails()
            initIngredients()
            initDrinks()
            initCocktailComponents()
            initCocktailTags()
        }
    }

    private fun initCocktails() {
        cocktailDao.clearAll()
        listOf(
            CocktailModel(
                CT_MARY, "Bloody Mary", "Blah blah", 120f,
                "https://cdn1.foodviva.com/static-content/food-images/cocktail-recipes/best-bloody-mary-cocktail-recipe/best-bloody-mary-cocktail-recipe.jpg"
            ),
            CocktailModel(
                CT_RUS, "White Russian", "Blah blah", 200f,
                "http://s3-eu-west-1.amazonaws.com/jamieoliverprod/_int/rdb2/upload/1219_1_1403274925_lrg.jpg"
            ),
            CocktailModel(
                CT_SUNR, "Tequila Sunrise", "Blah blah", 180f,
                "https://cdn.diffords.com/contrib/stock-images/2017/8/49/201746c438fdcb534ea73fd518baa2a0e386.jpg"
            ),
            CocktailModel(
                CT_OLD, "Old Fashioned", "Blah blah", 240f,
                "https://i1.wp.com/www.foodrepublic.com/wp-content/uploads/2011/04/oldfashioned.jpg"
            )
        ).forEach {
            cocktailDao.insert(it)
        }
    }

    private fun initIngredients() {
        ingredientDao.clearAll()
        listOf(
            IngredientModel(INGR_LEMON, "Lemon"),
            IngredientModel(INGR_CELERY, "Celery"),
            IngredientModel(INGR_TOMATO_JUICE, "Tomato Juice"),
            IngredientModel(INGR_TABASCO, "Tabasco"),
            IngredientModel(INGR_ICE, "Ice"),
            IngredientModel(INGR_CREAM, "Cream"),
            IngredientModel(INGR_GRENADINE, "Grenadine"),
            IngredientModel(INGR_ORANGE_JUICE, "Orange juice"),
            IngredientModel(INGR_ORANGE, "Orange"),
            IngredientModel(INGR_CANE_SUGAR, "Cane sugar")
        ).forEach { ingredientDao.insert(it) }
    }

    private fun initDrinks() {
        drinkDao.clearAll()
        listOf(
            DrinkModel(DRINK_RUM, "Rum", 40.0f, "https://avatars.mds.yandex.net/get-mpic/1909520/img_id2690672822588218490.jpeg/9hq"),
            DrinkModel(DRINK_VODKA, "Vodka", 40.0f, "https://produkty24.com.ua/db_pic/products/original/original_1459293098.84418988227844.jpg"),
            DrinkModel(DRINK_KAHLUA, "Kahlua", 20.0f, "http://www.happysanta.top/wp-content/uploads/2019/02/Kahlua.jpg"),
            DrinkModel(DRINK_TEQUILA, "Tequila", 40.0f, "http://beerhouse.by/wp-content/uploads/2019/08/tequila-jose-cuervo-reposado.jpg"),
            DrinkModel(DRINK_BOURBON, "Bourbon", 40.0f, "https://secure.ce-tescoassets.com/assets/HU/008/5010196091008/ShotType1_540x540.jpg")
        ).forEach { drinkDao.insert(it) }
    }

    private fun initCocktailComponents() {
        cocktailComponentDao.clearAll()
        listOf(
            cocktailComponentBuilder(CT_MARY).drink(DRINK_VODKA, 50f).build(),
            cocktailComponentBuilder(CT_MARY).ingredient(INGR_TOMATO_JUICE, 120f).build(),
            cocktailComponentBuilder(CT_MARY).ingredient(INGR_CELERY, 15f).build(),
            cocktailComponentBuilder(CT_MARY).ingredient(INGR_TABASCO, 1f).build(),
            cocktailComponentBuilder(CT_MARY).ingredient(INGR_ICE, 380f).build(),
            cocktailComponentBuilder(CT_RUS).drink(DRINK_VODKA, 30f).build(),
            cocktailComponentBuilder(CT_RUS).drink(DRINK_KAHLUA, 30f).build(),
            cocktailComponentBuilder(CT_RUS).ingredient(INGR_CREAM, 30f).build(),
            cocktailComponentBuilder(CT_RUS).ingredient(INGR_ICE, 120f).build(),
            cocktailComponentBuilder(CT_SUNR).drink(DRINK_TEQUILA, 50f).build(),
            cocktailComponentBuilder(CT_SUNR).ingredient(INGR_GRENADINE, 10f).build(),
            cocktailComponentBuilder(CT_SUNR).ingredient(INGR_ORANGE_JUICE, 150f).build(),
            cocktailComponentBuilder(CT_SUNR).ingredient(INGR_ICE, 180f).build(),
            cocktailComponentBuilder(CT_OLD).drink(DRINK_BOURBON, 50f).build(),
            cocktailComponentBuilder(CT_OLD).ingredient(INGR_ORANGE, 40f).build(),
            cocktailComponentBuilder(CT_OLD).ingredient(INGR_CANE_SUGAR, 5f).build(),
            cocktailComponentBuilder(CT_OLD).ingredient(INGR_ICE, 5f).build()
        ).forEach { cocktailComponentDao.insert(it) }
    }

    private fun initCocktailTags() {
        tagDao.clearAllCocktailTags()
        tagDao.clearAllTags()

        listOf(
            TagModel(TAG_SWEET, "Sweet"),
            TagModel(TAG_SOUR, "Sour"),
            TagModel(TAG_SOUR_SWEET, "Sour-sweet"),
            TagModel(TAG_FRESH, "Fresh"),
            TagModel(TAG_LONG, "Long"),
            TagModel(TAG_SHOT, "Shot"),
            TagModel(TAG_STRONG, "Strong"),
            TagModel(TAG_CLASSIC, "Classic"),
            TagModel(TAG_CITRUS, "Citrus")
        ).forEach { tagDao.insertTag(it) }

        listOf(
            CocktailsTagModel(CT_MARY, TAG_SOUR),
            CocktailsTagModel(CT_MARY, TAG_STRONG),
            CocktailsTagModel(CT_RUS, TAG_SWEET),
            CocktailsTagModel(CT_RUS, TAG_LONG),
            CocktailsTagModel(CT_RUS, TAG_STRONG),
            CocktailsTagModel(CT_SUNR, TAG_SOUR),
            CocktailsTagModel(CT_SUNR, TAG_CLASSIC),
            CocktailsTagModel(CT_SUNR, TAG_CITRUS),
            CocktailsTagModel(CT_OLD, TAG_SOUR_SWEET),
            CocktailsTagModel(CT_OLD, TAG_STRONG),
            CocktailsTagModel(CT_OLD, TAG_CLASSIC),
            CocktailsTagModel(CT_OLD, TAG_CITRUS)
        ).forEach { tagDao.insertCocktailTag(it) }
    }

    fun listAll() = cocktailDao.listAll()
        /*.map { list ->
            list.onEach {
                it.components.addAll(cocktailComponentDao.getComponentsForCocktail(it.id))
            }
        }*/.flowOn(Dispatchers.IO)

    companion object {
        private const val CT_MARY = 1L
        private const val CT_RUS = 2L
        private const val CT_SUNR = 3L
        private const val CT_OLD = 4L

        private const val INGR_LEMON = 1L
        private const val INGR_CELERY = 2L
        private const val INGR_TOMATO_JUICE = 3L
        private const val INGR_TABASCO = 4L
        private const val INGR_ICE = 5L
        private const val INGR_CREAM = 6L
        private const val INGR_GRENADINE = 7L
        private const val INGR_ORANGE_JUICE = 8L
        private const val INGR_ORANGE = 9L
        private const val INGR_CANE_SUGAR = 10L

        private const val DRINK_RUM = 1L
        private const val DRINK_VODKA = 2L
        private const val DRINK_KAHLUA = 3L
        private const val DRINK_TEQUILA = 4L
        private const val DRINK_BOURBON = 5L

        private const val TAG_SWEET = 1L
        private const val TAG_SOUR = 2L
        private const val TAG_SOUR_SWEET = 3L
        private const val TAG_FRESH = 4L
        private const val TAG_LONG = 5L
        private const val TAG_SHOT = 6L
        private const val TAG_STRONG = 7L
        private const val TAG_CLASSIC = 8L
        private const val TAG_CITRUS = 9L
    }
}

fun cocktailComponentBuilder(cocktailId: Long) = CocktailComponentModel.Builder(cocktailId)
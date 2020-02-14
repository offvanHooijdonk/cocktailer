package by.off.cocktailer.repo

import by.off.cocktailer.dao.CocktailComponentDao
import by.off.cocktailer.dao.CocktailDao
import by.off.cocktailer.dao.DrinkDao
import by.off.cocktailer.dao.IngredientDao
import by.off.cocktailer.model.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class CocktailRepo(
    private val cocktailDao: CocktailDao,
    private val cocktailComponentDao: CocktailComponentDao,
    private val drinkDao: DrinkDao,
    private val ingredientDao: IngredientDao
) {

    fun initData() {
        CoroutineScope(Dispatchers.IO).launch {
            initCocktails()
            initIngredients()
            initDrinks()
            initCocktailComponents()
        }
    }

    private fun initCocktails() {
        cocktailDao.clearAll()
        listOf(
            CocktailModel(
                CT_MARY, "Bloody Mary", "Blah blah",
                "https://cdn1.foodviva.com/static-content/food-images/cocktail-recipes/best-bloody-mary-cocktail-recipe/best-bloody-mary-cocktail-recipe.jpg"
            ),
            CocktailModel(
                CT_RUS, "White Russian", "Blah blah",
                "http://s3-eu-west-1.amazonaws.com/jamieoliverprod/_int/rdb2/upload/1219_1_1403274925_lrg.jpg"
            ),
            CocktailModel(
                CT_SUNR, "Tequila Sunrise", "Blah blah",
                "https://cdn.diffords.com/contrib/stock-images/2017/8/49/201746c438fdcb534ea73fd518baa2a0e386.jpg"
            ),
            CocktailModel(
                CT_OLD, "Old Fashioned", "Blah blah",
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
            IngredientModel(INGR_TOMATO, "Tomato Juice"),
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
            CocktailComponentModel(0, CT_MARY, DRINK_VODKA, ComponentType.DRINK, 50f, ComponentUnit.GRAM),
            CocktailComponentModel(0, CT_MARY, INGR_TOMATO, ComponentType.INGREDIENT, 120f, ComponentUnit.GRAM),
            CocktailComponentModel(0, CT_MARY, INGR_CELERY, ComponentType.INGREDIENT, 15f, ComponentUnit.GRAM),
            CocktailComponentModel(0, CT_MARY, INGR_TABASCO, ComponentType.INGREDIENT, 1f, ComponentUnit.GRAM),
            CocktailComponentModel(0, CT_MARY, INGR_ICE, ComponentType.INGREDIENT, 380f, ComponentUnit.GRAM),
            CocktailComponentModel(0, CT_RUS, DRINK_VODKA, ComponentType.DRINK, 30f, ComponentUnit.GRAM),
            CocktailComponentModel(0, CT_RUS, DRINK_KAHLUA, ComponentType.DRINK, 30f, ComponentUnit.GRAM),
            CocktailComponentModel(0, CT_RUS, INGR_CREAM, ComponentType.INGREDIENT, 30f, ComponentUnit.GRAM),
            CocktailComponentModel(0, CT_RUS, INGR_ICE, ComponentType.INGREDIENT, 120f, ComponentUnit.GRAM),
            CocktailComponentModel(0, CT_SUNR, DRINK_TEQUILA, ComponentType.DRINK, 50f, ComponentUnit.GRAM),
            CocktailComponentModel(0, CT_SUNR, INGR_GRENADINE, ComponentType.INGREDIENT, 10f, ComponentUnit.GRAM),
            CocktailComponentModel(0, CT_SUNR, INGR_ORANGE_JUICE, ComponentType.INGREDIENT, 150f, ComponentUnit.GRAM),
            CocktailComponentModel(0, CT_SUNR, INGR_ICE, ComponentType.INGREDIENT, 180f, ComponentUnit.GRAM),
            CocktailComponentModel(0, CT_OLD, DRINK_BOURBON, ComponentType.DRINK, 50f, ComponentUnit.GRAM),
            CocktailComponentModel(0, CT_OLD, INGR_ORANGE, ComponentType.INGREDIENT, 40f, ComponentUnit.GRAM),
            CocktailComponentModel(0, CT_OLD, INGR_CANE_SUGAR, ComponentType.INGREDIENT, 5f, ComponentUnit.GRAM),
            CocktailComponentModel(0, CT_OLD, INGR_ICE, ComponentType.INGREDIENT, 5f, ComponentUnit.GRAM)
        ).forEach { cocktailComponentDao.insert(it) }
    }

    fun listAll() = cocktailDao.listAll()
        .map { list ->
            list.onEach {
                it.components.addAll(
                    cocktailComponentDao.getComponentsForCocktail(it.id).map { c ->
                        when (c.ingredientType) {
                            ComponentType.INGREDIENT -> {
                                c.ingredient = ingredientDao.getById(c.ingredientId)
                            }
                            ComponentType.DRINK -> {
                                c.drink = drinkDao.getById(c.ingredientId)
                            }
                        }
                        c
                    })
            }
        }.flowOn(Dispatchers.IO)

    companion object {
        private const val CT_MARY = 1L
        private const val CT_RUS = 2L
        private const val CT_SUNR = 3L
        private const val CT_OLD = 4L

        private const val INGR_LEMON = 1L
        private const val INGR_CELERY = 2L
        private const val INGR_TOMATO = 3L
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
    }
}
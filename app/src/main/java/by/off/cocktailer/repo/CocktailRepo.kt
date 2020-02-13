package by.off.cocktailer.repo

import by.off.cocktailer.dao.CocktailComponentDao
import by.off.cocktailer.dao.CocktailDao
import by.off.cocktailer.dao.DrinkDao
import by.off.cocktailer.dao.IngridientDao
import by.off.cocktailer.model.CocktailModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch

class CocktailRepo(
    private val cocktailDao: CocktailDao,
    private val cocktailComponentDao: CocktailComponentDao,
    private val drinkDao: DrinkDao,
    private val ingridientDao: IngridientDao
) {

    fun initData() {
        CoroutineScope(Dispatchers.IO).launch {
            initCocktails()
        }
    }

    private fun initCocktails() {
        cocktailDao.clearAll()
        listOf(
            CocktailModel(
                1, "Bloody Mary", "Blah blah",
                "https://cdn1.foodviva.com/static-content/food-images/cocktail-recipes/best-bloody-mary-cocktail-recipe/best-bloody-mary-cocktail-recipe.jpg"
            ),
            CocktailModel(
                2, "White Russian", "Blah blah",
                "http://s3-eu-west-1.amazonaws.com/jamieoliverprod/_int/rdb2/upload/1219_1_1403274925_lrg.jpg"
            ),
            CocktailModel(
                3, "Tequila Sunrise", "Blah blah",
                "https://cdn.diffords.com/contrib/stock-images/2017/8/49/201746c438fdcb534ea73fd518baa2a0e386.jpg"
            ),
            CocktailModel(
                4, "Old Fashioned", "Blah blah",
                "https://i1.wp.com/www.foodrepublic.com/wp-content/uploads/2011/04/oldfashioned.jpg"
            )
        ).forEach {
            cocktailDao.insert(it)
        }
    }

    private fun initIngredients() {

    }

    fun listAll() = cocktailDao.listAll().flowOn(Dispatchers.IO)

    companion object {
        private const val COMP_MARY = 1L
        private const val COMP_RUS = 2L
        private const val COMP_SUNR = 3L
        private const val COMP_OLD = 4L

        private const val INGR_1 = 1L
    }
}
package daepoid.stockManager.testData;

import daepoid.stockManager.domain.item.UnitType;
import daepoid.stockManager.domain.recipe.DishType;
import daepoid.stockManager.domain.recipe.Ingredient;
import daepoid.stockManager.domain.recipe.Recipe;
import daepoid.stockManager.service.RecipeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@RequiredArgsConstructor
public class TestRecipeDataInit {

    private final RecipeService recipeService;

    @PostConstruct
    public void init() {
        recipeService.saveRecipe(Recipe.createRecipe("name1", 11000, 11.1, 10001.0, DishType.BOWL, "note1"));
        recipeService.saveRecipe(Recipe.createRecipe("name2", 21000, 12.1, 10002.0, DishType.BOWL, "note2"));
        recipeService.saveRecipe(Recipe.createRecipe("name3", 31000, 13.1, 10003.0, DishType.BASKET, "note3"));
        recipeService.saveRecipe(Recipe.createRecipe("name4", 41000, 14.1, 10004.0, DishType.BASKET, "note4"));
        recipeService.saveRecipe(Recipe.createRecipe("name5", 51000, 15.1, 10005.0, DishType.DESSERT, "note5"));
        recipeService.saveRecipe(Recipe.createRecipe("name6", 61000, 16.1, 10006.0, DishType.DESSERT, "note6"));
        recipeService.saveRecipe(Recipe.createRecipe("name7", 71000, 17.1, 10007.0, DishType.FLATTER, "note7"));
        recipeService.saveRecipe(Recipe.createRecipe("name8", 81000, 18.1, 10008.0, DishType.PASTA, "note8"));
        recipeService.saveRecipe(Recipe.createRecipe("name9", 91000, 19.1, 10009.0, DishType.MAIN, "note9"));
        recipeService.saveRecipe(Recipe.createRecipe("name10", 101000, 10.1, 10000.0, DishType.PANINI, "note10"));
    }
}

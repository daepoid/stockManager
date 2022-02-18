package daepoid.stockManager.repository;

import daepoid.stockManager.domain.recipe.DishType;
import daepoid.stockManager.domain.ingredient.Ingredient;
import daepoid.stockManager.domain.recipe.Recipe;
import daepoid.stockManager.domain.recipe.RecipeSearch;
import daepoid.stockManager.service.RecipeService;

import java.util.List;
import java.util.Optional;

public interface RecipeRepository {

    //==생성 로직==//
    Long save(Recipe recipe);

    //==조회 로직==//
    Recipe findById(Long id);
    List<Recipe> findAll();
    Recipe findByRecipeNumber(String recipeNumber);
    Recipe findByName(String name);
    List<Recipe> findByPrice(int price);
    List<Recipe> findByWeight(double weight);
    List<Recipe> findByIngredient(Ingredient ingredient);
    List<Recipe> findByDishType(DishType dishType);
    List<Recipe> findByRecipeSearch(RecipeSearch recipeSearch);

    //==수정 로직==//
    void changeRecipeNumber(Long recipeId, String recipeNumber);
    void changeName(Long recipeId, String name);
    void changePrice(Long recipeId, int price);
    void changeWeight(Long recipeId, double weight);
    void changeDishType(Long recipeId, DishType dishType);
    void changeIngredient(Long recipeId, List<Ingredient> ingredients);
    boolean addIngredient(Long recipeId, Ingredient ingredient);
    boolean removeIngredient(Long recipeId, Ingredient ingredient);
    void changeCost(Long recipeId, double cost);
    void updateCost(Long recipeId);
    void changeNotes(Long recipeId, String notes);

    //==삭제 로직==//
    void removeRecipe(Recipe recipe);
    void removeById(Long id);
}

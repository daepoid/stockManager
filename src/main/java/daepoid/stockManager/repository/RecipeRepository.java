package daepoid.stockManager.repository;

import daepoid.stockManager.domain.recipe.DishType;
import daepoid.stockManager.domain.recipe.Ingredient;
import daepoid.stockManager.domain.recipe.Recipe;

import java.util.List;
import java.util.Optional;

public interface RecipeRepository {

    //==생성 로직==//
    Long save(Recipe recipe);

    //==조회 로직==//
    Optional<Recipe> findById(Long id);
    List<Recipe> findAll();
    Optional<Recipe> findByName(String name);
    List<Recipe> findByPrice(Integer price);
    List<Recipe> findByUnitPrice(Double unitPrice);
    List<Recipe> findByWeight(Double weight);
    List<Recipe> findByIngredient(Ingredient ingredient);
    List<Recipe> findByDishType(DishType dishType);

    //==수정 로직==//
    void changeName(Long recipeId, String name);
    void changePrice(Long recipeId, Integer price);
    void changeUnitPrice(Long recipeId, Double unitPrice);
    void changeWeight(Long recipeId, Double weight);
    void changeDishType(Long recipeId, DishType dishType);
    void addIngredient(Long recipeId, Ingredient ingredient);
    void removeIngredient(Long recipeId, Ingredient ingredient);
    void changeNotes(Long recipeId, String notes);

    //==삭제 로직==//
    void removeRecipe(Recipe recipe);
    void removeById(Long id);
}

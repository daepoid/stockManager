package daepoid.stockManager.repository;

import daepoid.stockManager.domain.ingredient.Ingredient;

import java.util.List;

public interface IngredientRepository {

    //==생성 메서드==//
    Long saveIngredient(Ingredient ingredient);

    //==조회 메서드==//
    Ingredient findIngredient(Long ingredientId);
    List<Ingredient> findIngredients();
    List<Ingredient> findIngredientsByRecipe(Long recipeId);
}

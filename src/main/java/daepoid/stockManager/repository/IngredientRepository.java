package daepoid.stockManager.repository;

import daepoid.stockManager.domain.ingredient.Ingredient;
import daepoid.stockManager.domain.item.UnitType;

import java.util.List;

public interface IngredientRepository {

    //==생성 메서드==//
    Long saveIngredient(Ingredient ingredient);

    //==조회 메서드==//
    Ingredient findById(Long ingredientId);
    List<Ingredient> findAll();
    List<Ingredient> findByName(String name);
    List<Ingredient> findByRecipe(Long recipeId);
    List<Ingredient> findByUnitType(UnitType unitType);

    //==수정 메서드==//
    void changeName(Long ingredientId, String name);
    void changeQuantity(Long ingredientId, Integer quantity);
    void changeUnitType(Long ingredientId, UnitType unitType);
    void changeUnitPrice(Long ingredientId, Double unitPrice);
    void changeLoss(Long ingredientId, Double loss);
    void changeCost(Long ingredientId, Double cost);

    void updateCost(Long ingredientId);

    //==삭제 메서드==//
    void deleteIngredient(Long ingredientId);

}

package daepoid.stockManager.repository;

import daepoid.stockManager.domain.duty.Duty;
import daepoid.stockManager.domain.ingredient.Ingredient;
import daepoid.stockManager.domain.item.Item;
import daepoid.stockManager.domain.item.UnitType;

import java.util.List;

public interface IngredientRepository {

    //==생성 메서드==//
    Long saveIngredient(Ingredient ingredient);

    //==조회 메서드==//
    Ingredient findById(Long ingredientId);
    List<Ingredient> findAll();
    List<Ingredient> findAll(int maxResult);
    List<Ingredient> findAll(int firstResult, int maxResult);
    List<Ingredient> findByName(String name);
    List<Ingredient> findByRecipe(Long recipeId);
    List<Ingredient> findByUnitType(UnitType unitType);

    //==수정 메서드==//
    void changeItem(Long ingredientId, Item item);
    void changeName(Long ingredientId, String name);
    void changeQuantity(Long ingredientId, int quantity);
    void changeUnitType(Long ingredientId, UnitType unitType);
    void changeUnitPrice(Long ingredientId, double unitPrice);
    void changeLoss(Long ingredientId, double loss);
    void changeCost(Long ingredientId, double cost);

    void updateCost(Long ingredientId);

    //==삭제 메서드==//
    void deleteIngredient(Long ingredientId);

}

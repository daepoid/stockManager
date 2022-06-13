package daepoid.stockManager.repository;

import daepoid.stockManager.domain.food.Food;
import daepoid.stockManager.domain.food.Ingredient;
import daepoid.stockManager.domain.item.UnitType;
import daepoid.stockManager.domain.search.IngredientSearch;

import java.util.List;
import java.util.Optional;

public interface IngredientRepository {

    //==생성 메서드==//
    Long save(Ingredient ingredient);

    //==조회 메서드==//
    Optional<Ingredient> findById(Long ingredientId);
    List<Ingredient> findAll();
    List<Ingredient> findAll(int maxResult);
    List<Ingredient> findAll(int firstResult, int maxResult);
    List<Ingredient> findByItemId(Long itemId);
    List<Ingredient> findByName(String name);
    List<Ingredient> findByFoodId(Long foodId);

    List<Ingredient> findByIngredientSearch(IngredientSearch ingredientSearch);

    //==수정 메서드==//
    void changeQuantity(Long ingredientId, Double quantity);
    void changeUnitType(Long ingredientId, UnitType unitType);
    void changeUnitPrice(Long ingredientId, Double unitPrice);
    void changeLoss(Long ingredientId, Double loss);
    void changeFood(Long ingredientId, Food food);

    //==삭제 메서드==//
    void remove(Long ingredientId);
}

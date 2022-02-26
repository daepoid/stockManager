package daepoid.stockManager.repository.memory;

import daepoid.stockManager.domain.ingredient.Ingredient;
import daepoid.stockManager.domain.item.Item;
import daepoid.stockManager.domain.item.UnitType;
import daepoid.stockManager.repository.IngredientRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Repository
@RequiredArgsConstructor
public class MemoryIngredientRepository implements IngredientRepository {

    private static final Map<Long, Ingredient> store = new HashMap<>();
    private static Long sequence = 0L;

    @Override
    public Long saveIngredient(Ingredient ingredient) {
        ingredient.changeId(++sequence);
        store.put(ingredient.getId(), ingredient);
        return ingredient.getId();
    }

    @Override
    public Ingredient findById(Long ingredientId) {
        return store.get(ingredientId);
    }

    @Override
    public List<Ingredient> findAll() {
        return new ArrayList<>(store.values());
    }

    @Override
    public List<Ingredient> findAll(int maxResult) {
        return null;
    }

    @Override
    public List<Ingredient> findAll(int firstResult, int maxResult) {
        return null;
    }

    @Override
    public List<Ingredient> findByRecipe(Long recipeId) {
        return store.values().stream()
                .filter(ingredient -> ingredient.getRecipe().getId().equals(recipeId))
                .collect(Collectors.toList());
    }

    @Override
    public List<Ingredient> findByName(String name) {
        return store.values().stream()
                .filter(ingredient -> ingredient.getName().equals(name))
                .collect(Collectors.toList());
    }

    @Override
    public List<Ingredient> findByUnitType(UnitType unitType) {
        return store.values().stream()
                .filter(ingredient -> ingredient.getUnitType().equals(unitType))
                .collect(Collectors.toList());
    }

    @Override
    public void changeItem(Long ingredientId, Item item) {
        store.get(ingredientId).changeItem(item);
    }

    @Override
    public void changeName(Long ingredientId, String name) {
        store.get(ingredientId).changeName(name);
    }

    @Override
    public void changeQuantity(Long ingredientId, int quantity) {
        store.get(ingredientId).changeQuantity(quantity);
    }

    @Override
    public void changeUnitType(Long ingredientId, UnitType unitType) {
        store.get(ingredientId).changeUnitType(unitType);

    }

    @Override
    public void changeUnitPrice(Long ingredientId, double unitPrice) {
        store.get(ingredientId).changeUnitPrice(unitPrice);

    }

    @Override
    public void changeLoss(Long ingredientId, double loss) {
        store.get(ingredientId).changeLoss(loss);

    }

    @Override
    public void changeCost(Long ingredientId, double cost) {
        store.get(ingredientId).changeCost(cost);

    }

    @Override
    public void updateCost(Long ingredientId) {
        store.get(ingredientId).updateCost();
    }

    //==삭제 메서드==//
    @Override
    public void deleteIngredient(Long ingredientId) {
        store.remove(ingredientId);
    }
}
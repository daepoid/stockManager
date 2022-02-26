package daepoid.stockManager.service;

import daepoid.stockManager.domain.ingredient.Ingredient;
import daepoid.stockManager.domain.item.Item;
import daepoid.stockManager.domain.item.UnitType;
import daepoid.stockManager.repository.jpa.JpaIngredientRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class IngredientService {

    private final JpaIngredientRepository ingredientRepository;

    //==생성 메서드==//
    @Transactional
    public Long saveIngredient(Ingredient ingredient) {
        return ingredientRepository.saveIngredient(ingredient);
    }

    //==조회 메서드==//
    public Ingredient findIngredient(Long ingredientId) {
        return ingredientRepository.findById(ingredientId);
    }

    public List<Ingredient> findIngredients() {
        return ingredientRepository.findAll();
    }

    public List<Ingredient> findIngredients(int maxResult) {
        return ingredientRepository.findAll(maxResult);
    }

    public List<Ingredient> findIngredients(int firstResult, int maxResult) {
        return ingredientRepository.findAll(firstResult, maxResult);
    }

    public List<Ingredient> findByName(String name) {
        return ingredientRepository.findByName(name);
    }

    public List<Ingredient> findByRecipe(Long recipeId) {
        return ingredientRepository.findByRecipe(recipeId);
    }

    public List<Ingredient> findByUnitType(UnitType unitType) {
        return ingredientRepository.findByUnitType(unitType);
    }

    //==수정 메서드==//
    @Transactional
    public void changeItem(Long ingredientId, Item item) {
        ingredientRepository.changeItem(ingredientId, item);
    }

    @Transactional
    public void changeName(Long ingredientId, String name) {
        ingredientRepository.findById(ingredientId).changeName(name);
    }

    @Transactional
    public void changeQuantity(Long ingredientId, int quantity) {
        ingredientRepository.findById(ingredientId).changeQuantity(quantity);
    }

    @Transactional
    public void changeUnitType(Long ingredientId, UnitType unitType) {
        ingredientRepository.findById(ingredientId).changeUnitType(unitType);
    }

    @Transactional
    public void changeUnitPrice(Long ingredientId, double unitPrice) {
        ingredientRepository.findById(ingredientId).changeUnitPrice(unitPrice);
    }

    @Transactional
    public void changeLoss(Long ingredientId, double loss) {
        ingredientRepository.findById(ingredientId).changeLoss(loss);
    }

    @Transactional
    public void changeCost(Long ingredientId, double cost) {
        ingredientRepository.findById(ingredientId).changeCost(cost);
    }

    @Transactional
    public void updateCost(Long ingredientId) {
        ingredientRepository.findById(ingredientId).updateCost();
    }

    @Transactional
    public void deleteIngredient(Long ingredientId) {
        ingredientRepository.deleteIngredient(ingredientId);
    }
}
